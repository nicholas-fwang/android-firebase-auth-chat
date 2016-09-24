package io.fisache.firebase_auth_chat.ui.splash;

import dagger.Subcomponent;
import io.fisache.firebase_auth_chat.base.annotation.ActivityScope;

@ActivityScope
@Subcomponent(
        modules = {
                SplashActivityModule.class
        }
)
public interface SplashActivityComponent {
    SplashActivity inject(SplashActivity activity);
}
