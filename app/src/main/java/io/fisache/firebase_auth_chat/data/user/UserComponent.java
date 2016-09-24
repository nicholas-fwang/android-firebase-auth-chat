package io.fisache.firebase_auth_chat.data.user;

import dagger.Subcomponent;
import io.fisache.firebase_auth_chat.base.annotation.UserScope;
import io.fisache.firebase_auth_chat.data.friend.FriendComponent;
import io.fisache.firebase_auth_chat.data.friend.FriendModule;
import io.fisache.firebase_auth_chat.ui.main.MainActivityComponent;
import io.fisache.firebase_auth_chat.ui.main.MainActivityModule;

@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {
        MainActivityComponent plus(MainActivityModule activityModule);

        FriendComponent plus(FriendModule friendModule);
}
