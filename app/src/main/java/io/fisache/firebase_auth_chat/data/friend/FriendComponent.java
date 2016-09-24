package io.fisache.firebase_auth_chat.data.friend;

import dagger.Subcomponent;
import io.fisache.firebase_auth_chat.base.annotation.FriendScope;
import io.fisache.firebase_auth_chat.ui.chat.ChatActivityComponent;
import io.fisache.firebase_auth_chat.ui.chat.ChatActivityModule;

@FriendScope
@Subcomponent(
        modules = {
                FriendModule.class
        }
)
public interface FriendComponent {
    ChatActivityComponent plus(ChatActivityModule activityModule);
}
