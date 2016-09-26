package io.fisache.firebase_auth_chat.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.base.BaseActivity;
import io.fisache.firebase_auth_chat.base.BaseApplication;
import io.fisache.firebase_auth_chat.data.model.Chat;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;

public class ChatActivity extends BaseActivity {

    @Bind(R.id.rvChatList)
    RecyclerView rvChatList;
    @Bind(R.id.etMessage)
    EditText etMessage;
    @Bind(R.id.btnSend)
    Button btnSend;

    @Inject
    User user;
    @Inject
    Friend friend;
    @Inject
    ChatPresenter presenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    ChatAdapter chatAdapter;


    public static void startWithFriend(final BaseActivity activity, final Friend friend) {
        Intent intent = new Intent(activity, ChatActivity.class);
        BaseApplication.get(activity).createFriendComponent(friend);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        Log.d("fisache", "User : " + user.getUsername() + " Friend : " + friend.getUsername());
    }

    public void showChatList() {
        rvChatList.setAdapter(chatAdapter);
        rvChatList.setLayoutManager(linearLayoutManager);
    }

    public void showAddedChat(Chat chat) {
        chatAdapter.onChatAdded(chat);
    }

    @OnClick(R.id.btnSend)
    public void onClickSend() {
        String content = etMessage.getText().toString();
        etMessage.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        presenter.setChat(content);
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
        BaseApplication.get(this).releaseFriendComponent();
    }

    @Override
    protected void setupActivityComponent() {
        BaseApplication.get(this).getFriendComponent()
                .plus(new ChatActivityModule(this))
                .inject(this);
    }
}
