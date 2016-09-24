package io.fisache.firebase_auth_chat.ui.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.data.model.Chat;
import io.fisache.firebase_auth_chat.data.model.User;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ChatActivity activity;
    private User user;

    List<Chat> chats = new ArrayList<>();

    public ChatAdapter(ChatActivity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_chat, parent, false);
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ChatViewHolder)holder).bind(chats.get(position), user.getUsername());
    }

    public void onChatAdded(Chat chat) {
        chats.add(chat);
        notifyItemChanged(chats.size()-1);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
