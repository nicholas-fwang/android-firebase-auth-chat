package io.fisache.firebase_auth_chat.ui.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.data.model.Friend;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MainActivity activity;

    private final List<Friend> friends = new ArrayList<>();

    public MainAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MainViewHolder)holder).bind(friends.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserItemClicked(position);
            }
        });
    }

    private void onUserItemClicked(int adapterPosition) {
        activity.onUserItemClicked(friends.get(adapterPosition));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void onFriendAdded(Friend friend) {
        friends.add(friend);
        notifyItemChanged(friends.size()-1);
    }

    public void onFriendChanged(Friend friend) {
        int index = friends.indexOf(friend);
        if(index > -1) {
            friends.set(index, friend);
            notifyItemChanged(index);
        } else {
            // TODO : wrong friend
            Log.d("fisache", "onFriendChanged null");
        }
    }

    public void clearList() {
        friends.clear();
    }
}
