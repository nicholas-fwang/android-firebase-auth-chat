package io.fisache.firebase_auth_chat.ui.chat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.fisache.firebase_auth_chat.base.BasePresenter;
import io.fisache.firebase_auth_chat.data.model.Chat;
import io.fisache.firebase_auth_chat.data.model.Friend;
import io.fisache.firebase_auth_chat.data.model.User;
import io.fisache.firebase_auth_chat.data.source.remote.ChatService;
import io.fisache.firebase_auth_chat.data.source.remote.FriendsService;

public class ChatPresenter implements BasePresenter {
    private ChatActivity activity;
    private User user;
    private Friend friend;
    private FriendsService friendsService;
    private ChatService chatService;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;

    private ChildEventListener chatsListRef;

    public ChatPresenter(ChatActivity activity, User user, Friend friend, FriendsService friendsService, ChatService chatService) {
        this.activity = activity;
        this.user = user;
        this.friend = friend;
        this.friendsService = friendsService;
        this.chatService = chatService;

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void subscribe() {
        activity.showChatList();
        processChat();
    }

    @Override
    public void unsubscribe() {
        if(chatsListRef != null) {
            databaseRef.removeEventListener(chatsListRef);
        }
    }

    private void processChat() {
        chatsListRef = chatService.getChats().addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Chat chat = dataSnapshot.getValue(Chat.class);
                        activity.showAddedChat(chat);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }

    public void setChat(String content) {
        chatService.setChat(new Chat(user.getUsername(), content));
    }
}
