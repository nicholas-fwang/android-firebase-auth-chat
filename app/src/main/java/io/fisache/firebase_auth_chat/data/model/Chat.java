package io.fisache.firebase_auth_chat.data.model;

import android.support.annotation.NonNull;

public class Chat {
    @NonNull String uid;
    String userUid;
    String content;

    public Chat() {
    }

    public Chat(@NonNull String uid, String userUid, String content) {
        this.uid = uid;
        this.userUid = userUid;
        this.content = content;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
