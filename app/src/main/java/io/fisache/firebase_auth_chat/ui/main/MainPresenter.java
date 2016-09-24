package io.fisache.firebase_auth_chat.ui.main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.fisache.firebase_auth_chat.base.BasePresenter;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.FirebaseUserService;
import io.fisache.firebase_auth_chat.data.source.remote.UserService;

public class MainPresenter implements BasePresenter {
    private MainActivity activity;
    private User user;
    private FirebaseUserService firebaseUserService;
    private UserService userService;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;

    public MainPresenter(MainActivity activity, User user,
                              FirebaseUserService firebaseUserService, UserService userService) {
        this.activity = activity;
        this.user = user;
        this.firebaseUserService = firebaseUserService;
        this.userService = userService;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void subscribe() {
        if(user != null) {
            activity.sendMessageToBreakPreviousScreen();
        }
    }

    @Override
    public void unsubscribe() {

    }

    public void logout() {
        firebaseUserService.logOut(user.getProvider());
    }
}
