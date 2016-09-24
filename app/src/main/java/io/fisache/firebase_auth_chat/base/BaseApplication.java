package io.fisache.firebase_auth_chat.base;

import android.app.Application;
import android.content.Context;

import io.fisache.firebase_auth_chat.data.firebase.FirebaseModule;
import io.fisache.firebase_auth_chat.data.friend.FriendComponent;
import io.fisache.firebase_auth_chat.data.friend.FriendModule;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.user.UserComponent;
import io.fisache.firebase_auth_chat.data.user.UserModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;
    private UserComponent userComponent;
    private FriendComponent friendComponent;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .firebaseModule(new FirebaseModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public UserComponent createUserComponent(User user) {
        userComponent = appComponent.plus(new UserModule(user));
        return userComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    public FriendComponent createFriendComponent(Friend friend) {
        friendComponent = userComponent.plus(new FriendModule(friend));
        return friendComponent;
    }

    public FriendComponent getFriendComponent() {
        return friendComponent;
    }

    public void releaseFriendComponent() {
        friendComponent = null;
    }
}
