package io.fisache.firebase_auth_chat.ui.chat;

import dagger.Subcomponent;
import io.fisache.firebase_auth_chat.base.annotation.ActivityScope;

@ActivityScope
@Subcomponent(
        modules = {
                ChatActivityModule.class
        }
)
public interface ChatActivityComponent {
    ChatActivity inject(ChatActivity activity);
}
