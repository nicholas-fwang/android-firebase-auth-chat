package io.fisache.firebase_auth_chat.data.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Room {
    @NonNull String uid;
    @NonNull String user1Uid;
    @NonNull String user2Uid;
    List<Chat> chats = new ArrayList<>();

    public Room() {
    }

    public Room(@NonNull String uid, @NonNull String user1Uid, @NonNull String user2Uid) {
        this.uid = uid;
        this.user1Uid = user1Uid;
        this.user2Uid = user2Uid;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    @NonNull
    public String getUser1Uid() {
        return user1Uid;
    }

    @NonNull
    public String getUser2Uid() {
        return user2Uid;
    }

    public List<Chat> getChats() {
        return chats;
    }
}
