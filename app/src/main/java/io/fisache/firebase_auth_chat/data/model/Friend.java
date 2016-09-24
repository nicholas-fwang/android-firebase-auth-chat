package io.fisache.firebase_auth_chat.data.model;

import android.support.annotation.NonNull;

public class Friend {
    @NonNull String uid;
    String friendUid;

    public Friend() {
    }

    public Friend(@NonNull String uid, String friendUid) {
        this.uid = uid;
        this.friendUid = friendUid;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getFriendUid() {
        return friendUid;
    }

    public void setFriendUid(String friendUid) {
        this.friendUid = friendUid;
    }
}
