package io.fisache.firebase_auth_chat.data.source.remote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;

public class FriendsService {
    private User user;
    private DatabaseReference databaseRef;

    public FriendsService(User user) {
        this.user = user;
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getFriends() {
        return databaseRef.child("friends").child(user.getUsername());
    }

    // TODO
    public void getFriend(String username) {

    }

    public void setFriend(Friend friend) {
        String photo_url = friend.getPhoto_url();
        if(photo_url == null) {
            friend.setPhoto_url("NOT");
        }
        databaseRef.child("friends").child(user.getUsername()).child(friend.getUsername())
                .setValue(friend);
    }

    // TODO
    public void updateFriend(Friend friend) {

    }

    // TODO
    public void deleteFriend(String username) {

    }
}
