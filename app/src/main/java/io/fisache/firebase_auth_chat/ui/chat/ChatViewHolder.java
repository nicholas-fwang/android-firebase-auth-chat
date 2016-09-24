package io.fisache.firebase_auth_chat.ui.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fisache.firebase_auth_chat.R;
import io.fisache.firebase_auth_chat.data.model.Chat;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvReceive)
    TextView tvReceive;
    @Bind(R.id.tvSend)
    TextView tvSend;

    private View itemView;

    public ChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public void bind(Chat chat, String username) {
        if(chat.getUsername().equals(username)) {
            tvSend.setVisibility(View.VISIBLE);
            tvSend.setText(chat.getContent());
        } else {
            tvReceive.setVisibility(View.VISIBLE);
            tvReceive.setText(chat.getContent());
        }
    }
}
