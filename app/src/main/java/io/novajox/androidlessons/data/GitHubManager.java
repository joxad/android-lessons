package io.novajox.androidlessons.data;

import android.content.Context;

import java.util.List;

import io.novajox.androidlessons.R;
import io.novajox.androidlessons.data.model.Repo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jocelyn on 06/10/2017.
 */

public enum GitHubManager {
    INSTANCE;

    private Retrofit retrofit;
    private GitHubEndpoint service;

    public void init(Context context) {

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_config))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubEndpoint.class);


    }


    public Call<List<Repo>> getRepositories(String user) {
        return service.listRepos(user);
    }
}
