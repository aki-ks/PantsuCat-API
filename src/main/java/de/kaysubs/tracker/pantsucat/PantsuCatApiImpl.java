package de.kaysubs.tracker.pantsucat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.kaysubs.tracker.common.HttpUtil;
import de.kaysubs.tracker.common.exception.HttpException;
import de.kaysubs.tracker.pantsucat.exception.*;
import de.kaysubs.tracker.pantsucat.model.*;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PantsuCatApiImpl implements PantsuCatApi {
    private final static PantsuCatApiImpl INSTANCE = new PantsuCatApiImpl();
    public static PantsuCatApiImpl getInstance() {
        return INSTANCE;
    }

    private final static Gson GSON = new GsonBuilder().create();

    private <T> T parseJsonEntity(HttpEntity entity, Class<T> clazz) {
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if(charset == null)
            charset = Charset.forName("UTF-8");

        InputStream content;
        try {
            content = entity.getContent();
        } catch (IOException e) {
            throw new HttpException("Cannot read response content");
        }

        return GSON.fromJson(new InputStreamReader(content, charset), clazz);
    }

    @Override
    public SearchResponse search(SearchRequest query) {
        URI uri;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme("https")
                    .setHost("nyaa.pantsu.cat")
                    .setPath("/api/search");

            query.getCategories().ifPresent(categories -> {
                String categoryQuery = Arrays.stream(categories)
                        .map(c -> c.getSearchId())
                        .collect(Collectors.joining(","));

                builder.addParameter("c", categoryQuery);
            });

            query.getSearchTerm().ifPresent(term ->
                    builder.addParameter("q", term));

            query.getPage().ifPresent(page ->
                    builder.addParameter("page", Integer.toString(page)));

            query.getResultsPerPage().ifPresent(limit ->
                    builder.addParameter("limit", Integer.toString(limit)));

            query.getUserId().ifPresent(userId ->
                    builder.addParameter("userID", Integer.toString(userId)));

            query.getFromId().ifPresent(fromID ->
                    builder.addParameter("fromID", Integer.toString(fromID)));

            query.getStatus().ifPresent(status ->
                    builder.addParameter("s", Integer.toString(status.getId())));

            query.getMaxAge().ifPresent(maxage ->
                    builder.addParameter("maxage", Integer.toString(maxage)));

            query.getDateFilter().ifPresent(filter -> {
                filter.getFrom().ifPresent(from ->
                        builder.addParameter("fromDate", Integer.toString(from)));

                filter.getTo().ifPresent(to ->
                        builder.addParameter("toDate", Integer.toString(to)));

                builder.addParameter("dateType", filter.getDateUnit().getId());
            });

            query.getSizeFilter().ifPresent(filter -> {
                filter.getMin().ifPresent(min ->
                        builder.addParameter("minSize", Integer.toString(min)));

                filter.getMax().ifPresent(max ->
                        builder.addParameter("maxSize", Integer.toString(max)));

                builder.addParameter("sizeType", filter.getSizeUnit().getId());
            });

            query.getSortedBy().ifPresent(sort ->
                    builder.addParameter("sort", Integer.toString(sort.getId())));

            query.getOrder().ifPresent(order ->
                    builder.addParameter("order", Boolean.toString(order == SearchRequest.Order.ASCENDING)));

            query.getLanguages().ifPresent(languages -> {
                String languageQuery = Arrays.stream(languages)
                        .map(c -> c.getId())
                        .collect(Collectors.joining(","));

                builder.addParameter("lang", languageQuery);
            });

            uri = builder.build();
        } catch (URISyntaxException e) {
            throw new HttpException("Cannot build URL", e);
        }

        HttpGet get = new HttpGet(uri);
        get.setConfig(HttpUtil.WITH_TIMEOUT);

        HttpResponse response = HttpUtil.executeRequest(get);
        HttpUtil.requireStatusCode(response, 200);

        return parseJsonEntity(response.getEntity(), SearchResponse.class);
    }

    @Override
    public TorrentInfo getTorrentInfo(int id) {
        HttpGet get = new HttpGet("https://nyaa.pantsu.cat/api/view/" + id);
        get.setConfig(HttpUtil.WITH_TIMEOUT);

        HttpResponse response = HttpUtil.executeRequest(get);
        HttpUtil.requireStatusCode(response, 200);

        return parseJsonEntity(response.getEntity(), TorrentInfo.class);
    }

    @Override
    public UserProfile getProfile(int id) {
        HttpGet post = new HttpGet("https://nyaa.pantsu.cat/api/profile?id=" + id);
        post.setConfig(HttpUtil.WITH_TIMEOUT);

        HttpResponse response = HttpUtil.executeRequest(post);
        HttpUtil.requireStatusCode(response, 200);

        GetUserProfilResponse profileResponse = parseJsonEntity(response.getEntity(), GetUserProfilResponse.class);

        if(!profileResponse.isOk())
            throw new GetProfileException(profileResponse.getErrors());

        return profileResponse.getUserData();
    }

    @Override
    public TorrentInfo uploadTorrent(UploadRequest request) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        request.getUsername().ifPresent(username ->
                builder.addTextBody("username", username));

        request.getName().ifPresent(name ->
                builder.addTextBody("name", name));

        request.getCategory().ifPresent(category ->
                builder.addTextBody("c", category.getSearchId()));

        request.getDescription().ifPresent(description ->
                // The API documentation claims that
                // this parameter must be called "description",
                // but seems to be wrong (17.02.2018)
                builder.addTextBody("desc", description));

        request.getStatus().ifPresent(status ->
                builder.addTextBody("status", Integer.toString(status.getId())));

        request.getWebsite().ifPresent(website ->
                builder.addTextBody("website_link", website));

        request.getLanguages().ifPresent(languages -> {
            for(Language language : languages)
                builder.addTextBody("languages", language.getId());
        });

        request.isAnonymous().ifPresent(anonymous ->
                builder.addTextBody("hidden", Boolean.toString(anonymous)));

        request.isRemake().ifPresent(remake ->
                builder.addTextBody("remake", Boolean.toString(remake)));

        request.getMagnetLink().ifPresent(magnet ->
                builder.addTextBody("magnet", magnet));

        request.getSeedFile().ifPresent(seedFile -> {
            ContentType torrentMime = ContentType.create("application/x-bittorrent");
            builder.addBinaryBody("torrent", seedFile, torrentMime, seedFile.getName());
        });

        HttpPost post = new HttpPost("https://nyaa.pantsu.cat/api/upload");
        post.setConfig(HttpUtil.WITH_TIMEOUT);
        request.getApiKey().ifPresent(apiKey ->
                post.addHeader("Authorization", apiKey));
        post.setEntity(builder.build());

        HttpResponse httpResponse = HttpUtil.executeRequest(post);
        HttpUtil.requireStatusCode(httpResponse, 200);

        UploadResponse response = parseJsonEntity(httpResponse.getEntity(), UploadResponse.class);

        if(!response.isOk())
            throw new TorrentUploadException(response.getErrors());

        return response.getData();
    }

    @Override
    public void updateTorrent(UpdateRequest request) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        request.getUsername().ifPresent(username ->
                builder.addTextBody("username", username));

        request.getTorrentId().ifPresent(torrentId ->
                // The API documentation claims that
                // this parameter must in lowercase,
                // but seems to be wrong (17.02.2018)
                builder.addTextBody("ID", Integer.toString(torrentId)));

        request.getName().ifPresent(name ->
                builder.addTextBody("name", name));

        request.getCategory().ifPresent(category ->
                builder.addTextBody("c", category.getSearchId()));

        request.getDescription().ifPresent(description ->
                // The API documentation claims that
                // this parameter must be called "description",
                // but seems to be wrong (17.02.2018)
                builder.addTextBody("desc", description));

        request.getStatus().ifPresent(status ->
                builder.addTextBody("status", Integer.toString(status.getId())));

        request.getWebsite().ifPresent(website ->
                builder.addTextBody("website_link", website));

        request.isAnonymous().ifPresent(anonymous ->
                builder.addTextBody("hidden", Boolean.toString(anonymous)));

        request.isRemake().ifPresent(remake ->
                builder.addTextBody("remake", Boolean.toString(remake)));

        request.getLanguages().ifPresent(languages -> {
            for(Language language : languages)
                builder.addTextBody("languages", language.getId());
        });

        HttpPut put = new HttpPut("https://nyaa.pantsu.cat/api/update");
        put.setConfig(HttpUtil.WITH_TIMEOUT);

        request.getApiKey().ifPresent(apiKey ->
                put.addHeader("Authorization", apiKey));
        put.setEntity(builder.build());

        HttpResponse httpResponse = HttpUtil.executeRequest(put);
        HttpUtil.requireStatusCode(httpResponse, 200);

        UpdateResponse response = parseJsonEntity(httpResponse.getEntity(), UpdateResponse.class);

        if(!response.isOk())
            throw new TorrentUpdateException(response.getErrors());
    }

    @Override
    public AuthorizedUserProfile login(String username, String password) {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("username", username));
        form.add(new BasicNameValuePair("password", password));

        HttpPost post = new HttpPost("https://nyaa.pantsu.cat/api/login");
        post.setConfig(HttpUtil.WITH_TIMEOUT);
        post.setEntity(new UrlEncodedFormEntity(form, Consts.UTF_8));

        HttpResponse httpResponse = HttpUtil.executeRequest(post);
        HttpUtil.requireStatusCode(httpResponse, 200);

        LoginResponse loginResponse = parseJsonEntity(httpResponse.getEntity(), LoginResponse.class);

        if(!loginResponse.isOk())
            throw new LoginException(loginResponse.getErrors());

        return loginResponse.getUserData();
    }

    public CheckTokenResponse checkApiToken(String username, String apiToken) {
        URI uri;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("nyaa.pantsu.cat")
                    .setPath("/api/token/check")
                    .addParameter("username", username)
                    .build();
        } catch (URISyntaxException e) {
            throw new HttpException("Cannot build URL", e);
        }

        HttpGet get = new HttpGet(uri);
        get.addHeader("Authorization", apiToken);

        get.setConfig(HttpUtil.WITH_TIMEOUT);

        HttpResponse httpResponse = HttpUtil.executeRequest(get);
        HttpUtil.requireStatusCode(httpResponse, 200);

        return parseJsonEntity(httpResponse.getEntity(), CheckTokenResponse.class);
    }

    public RefreshTokenResponse refreshApiToken(String username, String apiToken){
        URI uri;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("nyaa.pantsu.cat")
                    .setPath("/api/token/refresh")
                    .addParameter("username", username)
                    .build();
        } catch (URISyntaxException e) {
            throw new HttpException("Cannot build URL", e);
        }

        HttpGet get = new HttpGet(uri);
        get.addHeader("Authorization", apiToken);

        get.setConfig(HttpUtil.WITH_TIMEOUT);

        HttpResponse httpResponse = HttpUtil.executeRequest(get);
        HttpUtil.requireStatusCode(httpResponse, 200);

        RefreshTokenResponse refreshResponse = parseJsonEntity(httpResponse.getEntity(), RefreshTokenResponse.class);

        if(!refreshResponse.isOk())
            throw new TokenRefreshException(refreshResponse.getErrors());

        return refreshResponse;
    }

}
