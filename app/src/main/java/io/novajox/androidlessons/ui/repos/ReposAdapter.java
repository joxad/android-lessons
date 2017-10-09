package io.novajox.androidlessons.ui.repos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.novajox.androidlessons.R;
import io.novajox.androidlessons.data.model.Repo;

/**
 * Created by Jocelyn on 09/10/2017.
 */

public class ReposAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private List<Repo> repoList;
    private int itemLayout = R.layout.item_repository;

    public ReposAdapter() {
        repoList = new ArrayList<>();
    }

    public void add(List<Repo> repos) {
        this.repoList.addAll(repos);
        notifyDataSetChanged();// this say to the recycler view the data has changed
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.updateTitle(repoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}
