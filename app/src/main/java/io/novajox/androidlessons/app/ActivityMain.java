package io.novajox.androidlessons.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.novajox.androidlessons.R;
import io.novajox.androidlessons.data.GitHubManager;
import io.novajox.androidlessons.data.model.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link ActivityMain} is the first screen you see when you start the application.
 * This is done in the src/main/AndroidManifest.xml
 */
public class ActivityMain extends AppCompatActivity {

    // this is a way to create a unique tag for all your activity
    private final static String TAG = ActivityMain.class.getSimpleName();
    private TextView textView;
    private ProgressBar progressBar;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        textView = (TextView) findViewById(R.id.tv_main_activity);
        bt = (Button) findViewById(R.id.activity_main_bt);
        progressBar = (ProgressBar) findViewById(R.id.activity_ws_progress);
        textView.setText(R.string.text_updated);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWs();
            }
        });
    }

    private void callWs() {
        progressBar.setVisibility(View.VISIBLE);
        bt.setVisibility(View.GONE);
        final Call<List<Repo>> repos = GitHubManager.INSTANCE.getRepositories("octocat");

        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d(TAG, "response ok");
                textView.setText(response.body().get(0).getName());
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityMain.this, "Success", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ActivityMain.this, ActivitySecond.class));
                finish();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "response fail");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityMain.this, "fail", Toast.LENGTH_LONG).show();

            }
        });
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
