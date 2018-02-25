package de.kaysubs.tracker.pantsucat;

import de.kaysubs.tracker.common.exception.*;
import de.kaysubs.tracker.pantsucat.exception.*;
import de.kaysubs.tracker.pantsucat.model.*;

public interface PantsuCatApi {
    static PantsuCatApi getInstance() {
        return PantsuCatApiImpl.getInstance();
    }

    /**
     * Search for torrents.
     * Can be used to list the latest torrents.
     *
     * Warning:
     * Some values in TorrentInfo's you got through this method might be incorrect.
     * Use the getTorrentInfo method to get all correct values for a result.
     *
     * @throws HttpException networking error
     */
    SearchResponse search(SearchRequest request);

    /**
     * Get detailed information about a torrent.
     *
     * @throws HttpException networking error
     */
    TorrentInfo getTorrentInfo(int torrentId);

    /**
     * Get information about a user
     *
     * @throws GetProfileException request failed (non existent user?)
     * @throws HttpException networking error
     */
    UserProfile getProfile(int userId);

    /**
     * Publish a torrent on pantsu.cat
     *
     * @throws TorrentUploadException your request was denied
     * @throws HttpException networking error
     */
    TorrentInfo uploadTorrent(UploadRequest request);

    /**
     * Edit one of your already published torrents.
     *
     * @throws TorrentUpdateException your request was denied
     * @throws HttpException networking error
     */
    void updateTorrent(UpdateRequest request);

    /**
     * Login to get your API token and account details.
     *
     * @throws LoginException login failed
     * @throws HttpException networking error
     */
    AuthorizedUserProfile login(String username, String password);

    /**
     * Check whether an API token is still valid.
     * The response does also include your account details.
     *
     * @throws HttpException networking error
     */
    CheckTokenResponse checkApiToken(String username, String apiToken);

    /**
     * Get a new API Token and your account details.
     *
     * Your current API token becomes invalid when you call this method.
     *
     * @throws TokenRefreshException request denied (invalid username or token?)
     * @throws HttpException networking error
     */
    RefreshTokenResponse refreshApiToken(String username, String apiToken);

}
