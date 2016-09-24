package io.fisache.firebase_auth_chat.ui.main;

import dagger.Subcomponent;
import io.fisache.firebase_auth_chat.base.annotation.ActivityScope;

@ActivityScope
@Subcomponent(
        modules = {
                MainActivityModule.class
        }

)
public interface MainActivityComponent {
    MainActivity inject(MainActivity activity);
}
