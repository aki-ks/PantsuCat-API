package de.kaysubs.tracker.pantsucat.util;

import de.kaysubs.tracker.pantsucat.PantsuCatApi;
import de.kaysubs.tracker.pantsucat.model.*;

import java.util.Iterator;

/**
 * Iterate over the result of a SearchRequest.
 * Loading next Pages is handled for you.
 *
 * When iterating over hundreds of torrents,
 * increasing the "resultsPerPage" variable of
 * the request will result in less requests
 * and will thereby result in a speedup and
 * decreased server load.
 */
public class SearchIterator implements Iterator<TorrentInfo> {
    private final SearchRequest request;
    private int page;
    private int index = 0;
    private TorrentInfo[] cachedPage;
    private boolean wasLastPage;

    public SearchIterator(SearchRequest request) {
        this.request = request;
        this.page = request.getPage().orElse(1);
    }

    /**
     * Load the next page into the cache if necessary
     */
    private void validateCache() {
        if(cachedPage == null || (index >= cachedPage.length && !wasLastPage))
            loadNextPage();
    }

    private void loadNextPage() {
        SearchResponse response = PantsuCatApi.getInstance()
                .search(request.setPage(page++));

        wasLastPage = response.getQueryRecordCount() != response.getTorrents().length;
        cachedPage = response.getTorrents();
        index = 0;
    }

    @Override
    public boolean hasNext() {
        validateCache();
        return index < cachedPage.length;
    }

    @Override
    public TorrentInfo next() {
        validateCache();
        return cachedPage[index++];
    }
}
