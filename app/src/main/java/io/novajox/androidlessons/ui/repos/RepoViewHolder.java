package io.novajox.androidlessons.ui.repos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import io.novajox.androidlessons.R;

/**
 * Created by Jocelyn on 09/10/2017.
 */

class RepoViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public RepoViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.item_repository_title);
    }


    public void updateTitle(String text) {
        textView.setText(text);
    }
}
