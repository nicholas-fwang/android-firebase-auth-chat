package io.fisache.firebase_auth_chat.base;

import javax.inject.Singleton;

import dagger.Component;
import io.fisache.firebase_auth_chat.data.firebase.FirebaseModule;
import io.fisache.firebase_auth_chat.data.user.UserComponent;
import io.fisache.firebase_auth_chat.data.user.UserModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                FirebaseModule.class
        }
)
public interface AppComponent {
    UserComponent plus(UserModule userModule);
}
