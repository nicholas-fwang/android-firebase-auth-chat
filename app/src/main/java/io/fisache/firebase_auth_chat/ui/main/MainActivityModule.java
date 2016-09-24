package io.fisache.firebase_auth_chat.ui.main;

import dagger.Module;
import dagger.Provides;
import io.fisache.firebase_auth_chat.base.annotation.ActivityScope;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.FirebaseUserService;
import io.fisache.firebase_auth_chat.data.source.remote.UserService;

@Module
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    MainActivity provideMainActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    MainPresenter provideMainPresenter(User user,
                                       FirebaseUserService firebaseUserService, UserService userService) {
        return new MainPresenter(activity, user, firebaseUserService, userService);

    }
}
