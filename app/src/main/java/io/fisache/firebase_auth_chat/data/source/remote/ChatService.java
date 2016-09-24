package io.fisache.firebase_auth_chat.data.source.remote;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.fisache.firebase_auth_chat.data.model.Chat;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;

public class ChatService {
    private User user;
    private Friend friend;
    private DatabaseReference databaseRef;

    public ChatService(User user, Friend friend) {
        this.user = user;
        this.friend = friend;
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getChats() {
        return databaseRef.child("chats").child(user.getUsername()).child(friend.getUsername());
    }

    public void setChat(Chat chat) {
        Map<String, Object> chatValues = chat.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        childUpdates.put("/chats/" + user.getUsername() + "/" + friend.getUsername() + "/" + uuid, chatValues);
        childUpdates.put("/chats/" + friend.getUsername() + "/" + user.getUsername() + "/" + uuid, chatValues);

        databaseRef.updateChildren(childUpdates);
    }
}
