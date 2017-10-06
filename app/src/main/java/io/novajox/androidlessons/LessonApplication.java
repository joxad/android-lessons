package io.novajox.androidlessons;

import android.app.Application;
import android.util.Log;

import io.novajox.androidlessons.data.GitHubManager;

/**
 * Created by Jocelyn on 06/10/2017.
 */

public class LessonApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LessonApplication.class.getSimpleName(), "Start application");
        GitHubManager.INSTANCE.init(this);
    }


}
