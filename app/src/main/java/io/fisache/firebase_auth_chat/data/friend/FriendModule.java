package io.fisache.firebase_auth_chat.data.friend;

import dagger.Module;
import dagger.Provides;
import io.fisache.firebase_auth_chat.base.annotation.FriendScope;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.ChatService;

@Module
public class FriendModule {
    private Friend friend;

    public FriendModule(Friend friend) {
        this.friend = friend;
    }

    @FriendScope
    @Provides
    Friend provideFriend() {
        return friend;
    }

    @FriendScope
    @Provides
    ChatService provideChatService(User user) {
        return new ChatService(user, friend);
    }
}
