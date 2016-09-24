package io.fisache.firebase_auth_chat.ui.main;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.fisache.firebase_auth_chat.base.BasePresenter;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.FirebaseUserService;
import io.fisache.firebase_auth_chat.data.source.remote.FriendsService;
import io.fisache.firebase_auth_chat.data.source.remote.UserService;

public class MainPresenter implements BasePresenter {
    private MainActivity activity;
    private User user;
    private FirebaseUserService firebaseUserService;
    private UserService userService;
    private FriendsService friendsService;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;

    private ChildEventListener friedsListRef;

    public MainPresenter(MainActivity activity, User user,
                         FirebaseUserService firebaseUserService, UserService userService, FriendsService friendsService) {
        this.activity = activity;
        this.user = user;
        this.firebaseUserService = firebaseUserService;
        this.userService = userService;
        this.friendsService = friendsService;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void subscribe() {
        if(user != null) {
            activity.sendMessageToBreakPreviousScreen();
        }
        activity.showFriendList();
        processFriends();
        // TODO : friends 정보 가져오기
    }

    @Override
    public void unsubscribe() {
        if(friedsListRef != null) {
            databaseRef.removeEventListener(friedsListRef);
        }
    }

    public void processFriends() {
        friedsListRef = friendsService.getFriends().addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.d("fisache", "friend :" + dataSnapshot.getValue());
                        Friend friend = dataSnapshot.getValue(Friend.class);
                        activity.showAddedFriend(friend);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Friend friend = dataSnapshot.getValue(Friend.class);
                        Log.d("fisache", "onChildChanged :" + dataSnapshot.getValue());
                        activity.showChangedFriend(friend);
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        // TODO : remove
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        // TODO : moved
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // TODO : cancel
                    }
                }
        );
    }

    public void setFriend(final String username) {
        userService.getUserByUsername(username).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()) {
                            activity.showNotExistFriend(username);
                        } else {
                            Friend friend = dataSnapshot.getValue(Friend.class);
                            friendsService.setFriend(friend);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        activity.showNotExistFriend(username);
                    }
                }
        );
    }

    public void logout() {
        firebaseUserService.logOut(user.getProvider());
    }
}
