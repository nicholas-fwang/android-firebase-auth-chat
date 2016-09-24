package io.fisache.firebase_auth_chat.ui.login;

import android.support.v7.app.AlertDialog;

import dagger.Module;
import dagger.Provides;
import io.fisache.firebase_auth_chat.base.annotation.ActivityScope;
import io.fisache.firebase_auth_chat.data.source.remote.FirebaseUserService;
import io.fisache.firebase_auth_chat.data.source.remote.UserService;

@Module
public class LoginActivityModule {

    private LoginActivity activity;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    LoginActivity provideLoginActivity() {
        return activity;
    }

    @ActivityScope
    @Provides
    LoginPresenter provideLoginPresenter(FirebaseUserService firebaseUserService, UserService userService) {
        return new LoginPresenter(activity, firebaseUserService, userService);
    }

    @Provides
    @ActivityScope
    AlertDialog.Builder provideAlerDialogBuilder() {
        return new AlertDialog.Builder(activity);
    }
}
