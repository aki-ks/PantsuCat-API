package de.kaysubs.tracker.pantsucat.examples;

import de.kaysubs.tracker.pantsucat.PantsuCatApi;
import de.kaysubs.tracker.pantsucat.model.*;
import de.kaysubs.tracker.pantsucat.util.SearchIterator;

public class SearchExamples {

    private final static PantsuCatApi api = PantsuCatApi.getInstance();

    public static TorrentInfo getLatestUpload() {
        // Search/List torrents without any filters
        SearchResponse result = api.search(new SearchRequest());

        // Get the first / most recent result
        return result.getTorrents()[0];
    }

    public static void searchByTerm() {
        api.search(new SearchRequest().setSearchTerm("Overlord II"));
    }

    public static void getTorrentsOfUser() {
        // UserId of 'k subs
        // https://nyaa.pantsu.cat/user/18031
        int userId = 18031;

        api.search(new SearchRequest().setUserId(userId));
    }

    // The "SearchIterator" might be a valuable utility when working with multiple pages.
    public static void workWithMultiplePages(SearchRequest request) {
        api.search(new SearchRequest()); // load page 1
        api.search(new SearchRequest().setPage(2)); // load page 2
        api.search(new SearchRequest().setPage(3)); // load page 3

        // load 2nd page where each page contains 100 torrents
        api.search(new SearchRequest().setPage(2).setResultsPerPage(100));

    }

    public static void searchIteratorExample() {
        System.out.println("'k subs did upload following Torrents:");

        new SearchIterator(new SearchRequest().setUserId(18031))
                .forEachRemaining(torrent -> System.out.println(torrent.getName()));
    }

    public static void sortAndOrder(SearchRequest request) {
        // Sort Torrents by size and grab the first one
        TorrentInfo largestTorrent =
                api.search(new SearchRequest().setSortedBy(SearchRequest.Sort.SIZE))
                        .getTorrents()[0];

        // Sort result in ascending order,
        // so the first result will be the
        // smallest and the last the largest
        TorrentInfo smallestTorrent =
                api.search(new SearchRequest()
                        .setSortedBy(SearchRequest.Sort.SIZE)
                        .setOrder(SearchRequest.Order.ASCENDING)
                ).getTorrents()[0];

    }

    public static SearchResponse filterByLanguage() {
        // Search for Torrents that are translated to german AND english
        return api.search(new SearchRequest().setLanguages(Language.GERMAN, Language.ENGLISH));
    }

    public static void searchByCategory() {
        api.search(new SearchRequest().setCategories(Category.Anime.Raw));

        // Search for english or non-english translated animes
        api.search(new SearchRequest().setCategories(Category.Anime.English, Category.Anime.NonEnglish));
    }

}
