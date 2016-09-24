package io.fisache.firebase_auth_chat.ui.splash;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import io.fisache.firebase_auth_chat.base.BasePresenter;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.UserService;

public class SplashPresenter implements BasePresenter {

    SplashActivity activity;
    UserService userService;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;

    public SplashPresenter(SplashActivity activity, UserService userService) {
        this.activity = activity;
        this.userService = userService;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void unsubscribe() {
        if(authListener != null) {
            firebaseAuth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void subscribe() {
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user == null) {
                    activity.showLoginActivity();
                } else {
                    processLogin(user);
                }
            }
        };

        firebaseAuth.addAuthStateListener(authListener);
    }

    private void processLogin(FirebaseUser user) {
        userService.getUser(user.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        if(user == null || user.getUsername() == null) {
                            activity.showLoginActivity();
                        } else {
                            activity.showMainActivity(user);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        activity.showLoginActivity();
                    }
                }
        );
    }
}
