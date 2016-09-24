package io.fisache.firebase_auth_chat.data.model;

import android.support.annotation.NonNull;

public class Friend {
    @NonNull String username;
    String photo_url;

    public Friend() {
    }

    public Friend(@NonNull String username, String photo_url) {
        this.username = username;
        this.photo_url = photo_url;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
