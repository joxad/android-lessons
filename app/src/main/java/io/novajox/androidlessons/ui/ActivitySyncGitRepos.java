package io.novajox.androidlessons.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.novajox.androidlessons.R;
import io.novajox.androidlessons.data.GitHubManager;
import io.novajox.androidlessons.data.model.Repo;
import io.novajox.androidlessons.ui.repos.ReposAdapter;
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
    private RecyclerView recyclerView;
    private Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_git_repositories);
        initViews();
        setupRecyclerView();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.activity_main_recycler_view);
        etUser = findViewById(R.id.et_user);
        bt = findViewById(R.id.activity_main_bt);
        progressBar = findViewById(R.id.activity_ws_progress);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWs();
            }
        });
        etUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    callWs();
                    return true;
                }
                return false;
            }
        });
    }


    private ReposAdapter repoAdapter;

    /***
     * A recyclerview need 2 things to works :
     * - A LayoutManager that tell the recycler how to handle the items
     * - An adapter that will contains all the items
     */
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        repoAdapter = new ReposAdapter();
        recyclerView.setAdapter(repoAdapter);
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
                repoAdapter.add(repos);
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
