package de.kaysubs.tracker.pantsucat.model;

import de.kaysubs.tracker.pantsucat.util.ConversionUtils;

import java.util.Date;
import java.util.Optional;

public class UserProfile {
    private int user_id;
    private String username;
    private int status;
    private String md5;
    private String created_at;
    private int liking_count;
    private int liked_count;

    public enum UserStatus {
        DEFAULT(0), TRUSTED(1), MODERATOR(2), SCRAPED(3);

        public static UserStatus fromId(int id) {
            for(UserStatus status : values())
                if(status.getId() == id)
                    return status;

            throw new IllegalArgumentException("No UserStatus with id " + id);
        }

        private final int id;

        UserStatus(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public UserStatus getStatus() {
        return UserStatus.fromId(status);
    }

    /**
     * MD5 hash of email address.
     *
     * Can be used to fetch the user's avatar from gravatar:
     * https://gravatar.com/avatar/{hash}
     */
    public Optional<String> getEmailHash() {
        return Optional.ofNullable(md5).filter(s -> !s.isEmpty());
    }

    public Date getCreatedAt() {
        return ConversionUtils.parseDate(created_at);
    }

    public int getLikingCount() {
        return liking_count;
    }

    public int getLikedCount() {
        return liked_count;
    }
}
