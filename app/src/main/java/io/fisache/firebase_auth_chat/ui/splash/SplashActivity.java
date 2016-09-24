package io.fisache.firebase_auth_chat.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.fisache.firebase_auth_chat.ui.login.LoginActivity;
import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.base.BaseActivity;
import io.fisache.firebase_auth_chat.base.BaseApplication;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    public void showLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getAppComponent()
                .plus(new SplashActivityModule(this))
                .inject(this);
    }

    public void showMainActivity(User user) {
        MainActivity.startWithUser(this, user);
    }

}
