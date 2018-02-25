package de.kaysubs.tracker.pantsucat.model;

public enum TorrentStatus {
    NORMAL(0),
    REMAKE(2),
    BY_TRUSTED_USER(3),
    A_PLUS(4);

    public static TorrentStatus fromId(int id) {
        for(TorrentStatus status : TorrentStatus.values())
            if(status.getId() == id)
                return status;

        throw new IllegalArgumentException("No TorrentStatus with id " + id);
    }

    private final int id;

    TorrentStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}