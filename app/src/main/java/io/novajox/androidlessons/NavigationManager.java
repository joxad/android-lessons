package io.novajox.androidlessons;

import android.content.Context;
import android.content.Intent;

import io.novajox.androidlessons.app.ActivitySecond;
import io.novajox.androidlessons.data.model.Repo;

/**
 * Created by Jocelyn on 09/10/2017.
 */

public class NavigationManager {

    public static String EXTRA_REPO = "Repo";

    public static void goToActivity2(Context context, Repo repo) {
        context.startActivity(new Intent(context, ActivitySecond.class)
                .putExtra(EXTRA_REPO, repo));
    }
}
