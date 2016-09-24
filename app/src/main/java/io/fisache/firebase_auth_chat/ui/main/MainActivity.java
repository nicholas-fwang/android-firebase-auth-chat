package io.fisache.firebase_auth_chat.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.base.BaseActivity;
import io.fisache.firebase_auth_chat.base.BaseApplication;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.ui.login.LoginActivity;

public class MainActivity extends BaseActivity {
    public static final int REQUEST_COMPLETED = 1003;

    @Bind(R.id.btnLogout)
    Button btnLogout;

    @Inject
    User user;
    @Inject
    MainPresenter presenter;

    public static void startWithUser(final BaseActivity activity, final User user) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("finisher", new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                activity.finish();
            }
        });
        BaseApplication.get(activity).createUserComponent(user);
        activity.startActivityForResult(intent, REQUEST_COMPLETED);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d("fisache", user.getNickname() + " " + user.getEmail() + " " + user.getName());
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

    public void sendMessageToBreakPreviousScreen() {
        ((ResultReceiver)getIntent().getParcelableExtra("finisher")).
                send(MainActivity.REQUEST_COMPLETED, new Bundle());
    }

    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this)
                .getUserComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @OnClick(R.id.btnLogout)
    public void onClickLogout() {
        presenter.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        BaseApplication.get(this).releaseUserComponent();
        startActivity(intent);
        finish();
    }
}
