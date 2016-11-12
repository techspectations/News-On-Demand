package com.manorama.techspectations.ui.home.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.OnRecyclerItemClickListener;
import com.manorama.techspectations.model.News;

import java.util.ArrayList;

/**
 * Created by Godwin Joseph on 12-11-2016 15:06 for Techspectations application.
 */

public class FollwinfRecyclerAdapter extends RecyclerView.Adapter<FollowingViewHolder> {
    ArrayList<News> newses = new ArrayList<>();
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    @Override
    public FollowingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_following_item, null);

        return new FollowingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FollowingViewHolder holder, int position) {
        News news = newses.get(position);
        String header = news.getNewsHeading();
        Uri uri = Uri.parse(news.getNewsMobileImageUrl());
        holder.tvTitle.setText(header);
        holder.sdvFollwing.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public ArrayList<News> getNewses() {
        return newses;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
        notifyDataSetChanged();
    }

    public OnRecyclerItemClickListener getOnRecyclerItemClickListener() {
        return onRecyclerItemClickListener;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }
}
