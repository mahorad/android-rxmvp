package mahorad.com.android_rxmvp.base;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by mahan on 2017-08-23.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
