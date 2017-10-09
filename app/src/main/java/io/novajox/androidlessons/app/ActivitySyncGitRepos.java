package io.novajox.androidlessons.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * {@link ActivitySyncGitRepos} is the first screen you see when you start the application.
 * This is done in the src/main/AndroidManifest.xml
 */
public class ActivitySyncGitRepos extends AppCompatActivity {

    // this is a way to create a unique tag for all your activity
    private final static String TAG = ActivitySyncGitRepos.class.getSimpleName();
    private EditText etUser;
    private ProgressBar progressBar;
    private TextView textView;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_git_repositories);
        textView = (TextView) findViewById(R.id.activity_ws_tv_result);
        etUser = (EditText) findViewById(R.id.et_user);
        bt = (Button) findViewById(R.id.activity_main_bt);
        progressBar = (ProgressBar) findViewById(R.id.activity_ws_progress);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWs();
            }
        });
    }

    /**
     * this method will show a progress bar and start the second activity with some elements
     */
    private void callWs() {
        progressBar.setVisibility(View.VISIBLE);
        bt.setVisibility(View.GONE);
        GitHubManager.INSTANCE.getRepositories(etUser.getText().toString()).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d(TAG, "response ok");
                List<Repo> repos = response.body();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivitySyncGitRepos.this, "Success", Toast.LENGTH_LONG).show();
                if (repos != null) {
                    textView.setText(repos.size() + " repos have been found !");
                } else {
                    textView.setText("Nothing has been found !");

                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "response fail");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivitySyncGitRepos.this, "fail", Toast.LENGTH_LONG).show();
                bt.setVisibility(View.VISIBLE);

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
