package io.fisache.firebase_auth_chat.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.base.BaseActivity;
import io.fisache.firebase_auth_chat.base.BaseApplication;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.ui.login.LoginActivity;

public class MainActivity extends BaseActivity {
    public static final int REQUEST_COMPLETED = 1003;

    @Bind(R.id.btnLogout)
    Button btnLogout;
    @Bind(R.id.rvUserList)
    RecyclerView rvUserList;
    @Bind(R.id.btnAdd)
    FloatingActionButton btnAdd;

    @Inject
    User user;
    @Inject
    MainPresenter presenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    MainAdapter mainAdapter;
    @Inject
    AlertDialog.Builder addAlertDialog;

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
        Log.d("fisache", user.getUsername() + " " + user.getEmail() + " " + user.getName());
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

    public void onUserItemClicked(Friend friend) {
        // TODO : go chat room
    }

    public void showAddedFriend(Friend friend) {
        mainAdapter.onFriendAdded(friend);
    }

    public void showChangedFriend(Friend friend) {
        mainAdapter.onFriendChanged(friend);
    }

    public void showFriendList() {
        rvUserList.setAdapter(mainAdapter);
        rvUserList.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.btnLogout)
    public void onClickLogout() {
        presenter.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        BaseApplication.get(this).releaseUserComponent();
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnAdd)
    public void onClickAdd() {
        addAlertDialog.setTitle("Insert your friend name");
        addAlertDialog.setMessage("Be sure to enter");

        final EditText etLogin = new EditText(this);
        etLogin.setSingleLine();
        addAlertDialog.setView(etLogin);

        addAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                presenter.setFriend(etLogin.getText().toString());
            }
        });

        addAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        addAlertDialog.show();
    }

    public void showNotExistFriend(String username) {
        Toast.makeText(this, username + " not exist", Toast.LENGTH_LONG).show();
    }
}
