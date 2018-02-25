package de.kaysubs.tracker.pantsucat.model;

import de.kaysubs.tracker.pantsucat.util.ConversionUtils;

import java.util.Arrays;
import java.util.Date;

public class TorrentInfo {
    private int id;
    private String name;
    private int status;
    private String hash;
    private String date;
    private long filesize;
    private String description;
    private Comment[] comments;
    private String sub_category;
    private String category;
    private int anidbid;
    private int vndbid;
    private int vgmdbid;
    private String dlsite;
    private String videoquality;
    // This API-feature seems to be kinda broken, so I'm not sure how to implement it.
    //private Tag[] tags;
    private int uploader_id;
    private String uploader_name;
    private String uploader_old;
    private String website_link;
    private String[] languages;
    private String magnet;
    private String torrent;
    private int seeders;
    private int leechers;
    private int completed;
    private String last_scrape;
    private File[] file_list;

    public static class Comment {
        private String username;
        private int user_id;
        private String user_avatar;
        private String user_status;
        private String content;
        private String date;

        public String getUsername() {
            return username;
        }

        public int getUserId() {
            return user_id;
        }

        public String getUserAvatar() {
            return user_avatar;
        }

        /**
         * Returns whether the uploader of this torrent wrote the message
         */
        public boolean isFromUploader() {
            return getUserStatus().equals("userstatus_uploader");
        }

        public String getUserStatus() {
            return user_status;
        }

        public String getMessage() {
            return content;
        }

        public Date getDate() {
            return ConversionUtils.parseDate(date);
        }
    }

    public static class File {
        private String path;
        private long filesize;

        public String getPath() {
            return path;
        }

        public long getFilesize() {
            return filesize;
        }
    }

    public enum Quality {
        SD("sd"),           // 480p
        HD("hd"),           // 720p
        FULL_HD("full_hd"), // 1080p
        BLURAY("bluray");

        public static Quality fromId(String id) {
            for(Quality quality : Quality.values())
                if(quality.getId().equals(id))
                    return quality;

            throw new RuntimeException("Unknown quality name " + id);
        }

        private final String id;

        Quality(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TorrentStatus getStatus() {
        return TorrentStatus.fromId(status);
    }

    public String getHash() {
        return hash;
    }

    public String getUploadDate() {
        return date;
    }

    public long getFilesize() {
        return filesize;
    }

    public String getDescription() {
        return description;
    }

    public Comment[] getComments() {
        return comments;
    }

    public Category getCategory() {
        int mainCategory = Integer.parseInt(this.category);
        int subCategory = Integer.parseInt(this.sub_category);
        return Category.fromId(mainCategory, subCategory);
    }

    /**
     * Id of anime on AniDB.net
     */
    public int getAnidbid() {
        return anidbid;
    }

    public int getVndbid() {
        return vndbid;
    }

    public int getVgmdbid() {
        return vgmdbid;
    }

    public String getDlsite() {
        return dlsite;
    }

    public Quality getVideoQuality() {
        return videoquality.isEmpty() ? null : Quality.fromId(videoquality);
    }

    public int getUploaderId() {
        return uploader_id;
    }

    public String getUploaderName() {
        return uploader_name;
    }

    public String getUploaderOld() {
        return uploader_old;
    }

    public String getWebsiteLink() {
        return website_link;
    }

    public Language[] getLanguages() {
        return Arrays.stream(this.languages)
                .map(Language::forId)
                .toArray(Language[]::new);
    }

    public String getMagnet() {
        return magnet;
    }

    /**
     * Url to download the .torrent file.
     */
    public String getTorrent() {
        return torrent;
    }

    public int getSeeders() {
        return seeders;
    }

    public int getLeechers() {
        return leechers;
    }

    public int getCompleted() {
        return completed;
    }

    public String getLastScrapeTime() {
        return last_scrape;
    }

    public File[] getFileList() {
        return file_list;
    }
}
