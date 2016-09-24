package io.fisache.firebase_auth_chat.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class User {
    @NonNull String uid;
    @Nullable String username;
    @Nullable String email;
    @VisibleForTesting
    @Nullable String password;
    @Nullable String provider;
    @Nullable String photo_url;
    @Nullable String name;

    public static User newInstance(FirebaseUser firebaseUser, UserInfo provider) {
        User user = new User(firebaseUser.getUid());
        user.setProvider(provider.getProviderId());
        // TODO : refactoring
        if(provider.getProviderId().equals("password")) {
            user.setEmail(firebaseUser.getEmail());
        }
        else if(provider.getProviderId().equals("facebook.com")) {
            user.setName(provider.getDisplayName());
            user.setPhoto_url(provider.getPhotoUrl().toString());
        }
        else if(provider.getProviderId().equals("google.com")) {
            user.setEmail(firebaseUser.getEmail());
            user.setName(provider.getDisplayName());
            user.setPhoto_url(provider.getPhotoUrl().toString());
        } else {

        }

        return user;
    }

    public User() {
    }

    public User(String uid) {
        this.uid = uid;
    }

    public User(String uid, String username, String email, String provider, String photo_url, String name) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.provider = provider;
        this.photo_url = photo_url;
        this.name = name;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(@Nullable String photo_url) {
        this.photo_url = photo_url;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getProvider() {
        return provider;
    }

    public void setProvider(@Nullable String provider) {
        this.provider = provider;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(String nickname) {
        this.username = nickname;
    }
}
