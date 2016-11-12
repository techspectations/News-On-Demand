package com.manorama.techspectations.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.manorama.techspectations.R;
import com.manorama.techspectations.database.manager.DatabaseManager;
import com.manorama.techspectations.model.News;
import com.manorama.techspectations.ui.BaseActivity;
import com.manorama.techspectations.ui.home.adapter.FollwinfRecyclerAdapter;

import java.util.ArrayList;

public class FollowingListActivity extends BaseActivity {
    RecyclerView rvFollowingList;
    FollwinfRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_list);
        setupToolbar("Following", true);
    }

    @Override
    protected Context initializeContext() {
        return this;
    }

    @Override
    protected void initializeWidgets() {
        rvFollowingList = (RecyclerView) findViewById(R.id.rv_following_list);
        rvFollowingList.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
        mRecyclerAdapter = new FollwinfRecyclerAdapter();
        rvFollowingList.setAdapter(mRecyclerAdapter);
    }

    @Override
    protected void registerListeners() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFromDb();
    }

    private void updateFromDb() {
        ArrayList<News> newses = DatabaseManager.getInstance().getSubscribedArticles();
        mRecyclerAdapter.setNewses(newses);
    }
}
