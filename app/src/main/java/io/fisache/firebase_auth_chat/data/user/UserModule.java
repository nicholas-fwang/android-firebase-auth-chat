package io.fisache.firebase_auth_chat.data.user;

import dagger.Module;
import dagger.Provides;
import io.fisache.firebase_auth_chat.base.annotation.UserScope;
import io.fisache.firebase_auth_chat.data.model.User;

@Module
public class UserModule {
    User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }
}
