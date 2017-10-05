package io.novajox.androidlessons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * {@link ActivityMain} is the first screen you see when you start the application.
 * This is done in the src/main/AndroidManifest.xml
 */
public class ActivityMain extends AppCompatActivity {

    // this is a way to create a unique tag for all your activity
    private final static String TAG = ActivityMain.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final TextView textView = (TextView) findViewById(R.id.tv_main_activity);
        textView.setText(R.string.text_updated);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(R.string.text_clicked);
            }
        });
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
