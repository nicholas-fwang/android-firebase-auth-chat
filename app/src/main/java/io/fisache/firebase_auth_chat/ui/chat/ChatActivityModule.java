package io.fisache.firebase_auth_chat.ui.chat;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import io.fisache.firebase_auth_chat.base.annotation.ActivityScope;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.ChatService;
import io.fisache.firebase_auth_chat.data.source.remote.FriendsService;

@Module
public class ChatActivityModule {
    private ChatActivity activity;

    public ChatActivityModule(ChatActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    ChatActivity provideChatActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    ChatPresenter provideChatPresenter(User user, Friend friend, FriendsService friendsService, ChatService chatService) {
        return new ChatPresenter(activity, user, friend, friendsService, chatService);
    }

    @ActivityScope
    @Provides
    ChatAdapter provideChatAdapter(User user) {
        return new ChatAdapter(activity, user);
    }

    @ActivityScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(activity);
    }
}
