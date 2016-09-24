package io.fisache.firebase_auth_chat.data.user;

import dagger.Subcomponent;
import io.fisache.firebase_auth_chat.base.annotation.UserScope;

@UserScope
@Subcomponent(
        modules = {
                UserModule.class
        }
)
public interface UserComponent {
}
