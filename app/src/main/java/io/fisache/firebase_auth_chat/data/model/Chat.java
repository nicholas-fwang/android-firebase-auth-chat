package io.fisache.firebase_auth_chat.data.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Chat {
    String username;
    String content;

    public Chat() {
    }

    public Chat(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("content", content);

        return result;
    }
}
