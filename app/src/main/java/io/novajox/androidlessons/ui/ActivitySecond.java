package io.novajox.androidlessons.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.novajox.androidlessons.NavigationManager;
import io.novajox.androidlessons.R;
import io.novajox.androidlessons.data.model.Repo;

public class ActivitySecond extends AppCompatActivity {

    // this is a way to create a unique tag for all your activity
    private final static String TAG = ActivitySecond.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Repo repo = getIntent().getParcelableExtra(NavigationManager.EXTRA_REPO);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
