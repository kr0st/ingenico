package lv.javaguru.courses.ingenico.lecture4.common;

import java.time.LocalDateTime;

public class User {

    private final String nickname;

    private LocalDateTime createdAt;

    private String facebookId;

    private String twitterId;

    private boolean active;

    public User(String nickname, LocalDateTime createdAt, boolean active) {
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.active = active;
    }

    public User(String nickname,
                LocalDateTime createdAt,
                boolean active,
                String facebookId,
                String twitterId) {
        this(nickname, createdAt, active);
        this.facebookId = facebookId;
        this.twitterId = twitterId;
        this.active = active;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return nickname.equals(user.nickname);
    }

    @Override
    public int hashCode() {
        return nickname.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", twitterId='" + twitterId + '\'' +
                '}';
    }
}
