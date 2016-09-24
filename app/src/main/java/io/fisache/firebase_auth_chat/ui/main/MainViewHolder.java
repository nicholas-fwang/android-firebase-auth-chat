package io.fisache.firebase_auth_chat.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.data.model.Friend;

public class MainViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.ivAvatar)
    ImageView ivAatar;
    @Bind(R.id.tvUser)
    TextView tvUser;

    private View itemView;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public void bind(Friend friend) {
        if(!friend.getPhoto_url().equals("NOT")) {
            Picasso.with(itemView.getContext()).load(friend.getPhoto_url()).into(ivAatar);
        }
        tvUser.setText(friend.getUsername());
    }
}

