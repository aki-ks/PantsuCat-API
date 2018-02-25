package de.kaysubs.tracker.pantsucat.model;

public class SearchResponse {
    private int queryRecordCount;
    private int totalRecordCount;
    private TorrentInfo[] torrents;

    public TorrentInfo[] getTorrents() {
        return torrents;
    }

    /**
     * Maximum amount of torrents returned per page (see SearchRequest.getResultsPerPage).
     */
    public int getQueryRecordCount() {
        return queryRecordCount;
    }

    /**
     * Total amount of search results.
     */
    public int getTotalRecordCount() {
        return totalRecordCount;
    }
}
