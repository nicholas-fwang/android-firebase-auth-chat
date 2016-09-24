package io.fisache.firebase_auth_chat.base;

import javax.inject.Singleton;

import dagger.Component;
import io.fisache.firebase_auth_chat.data.firebase.FirebaseModule;
import io.fisache.firebase_auth_chat.data.user.UserComponent;
import io.fisache.firebase_auth_chat.data.user.UserModule;
import io.fisache.firebase_auth_chat.ui.login.LoginActivityComponent;
import io.fisache.firebase_auth_chat.ui.login.LoginActivityModule;
import io.fisache.firebase_auth_chat.ui.splash.SplashActivityComponent;
import io.fisache.firebase_auth_chat.ui.splash.SplashActivityModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                FirebaseModule.class
        }
)
public interface AppComponent {

    SplashActivityComponent plus(SplashActivityModule activityModule);

    LoginActivityComponent plus(LoginActivityModule activityModule);

    UserComponent plus(UserModule userModule);
}
