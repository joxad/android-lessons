package io.novajox.androidlessons.data;

import java.util.List;

import io.novajox.androidlessons.data.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubEndpoint {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}