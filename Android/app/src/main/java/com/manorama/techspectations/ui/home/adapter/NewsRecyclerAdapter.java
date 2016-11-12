package com.manorama.techspectations.ui.home.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.OnRecyclerItemClickListener;
import com.manorama.techspectations.model.BreakingNews;

import java.util.ArrayList;

/**
 * Created by Godwin Joseph on 10-11-2016 18:38 for Techspectations application.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    ArrayList<BreakingNews> breakingNewses = new ArrayList<>();
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, null);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final BreakingNews breakingNews = breakingNewses.get(position);
        String heading = breakingNews.getNewsHeading();
        String provider = breakingNews.getNewsProvider();

        Uri imageUri = Uri.parse(breakingNews.getNewsMobileImageUrl());

        holder.sdvThumbNail.setImageURI(imageUri);
        holder.tvUpdatedTime.setText(breakingNews.getTime());
        holder.tvHeading.setText(heading);
        holder.tvProvider.setText(provider);
        holder.llNewsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener!=null){
                    onRecyclerItemClickListener.onItemClicked(breakingNews,holder.llNewsItem,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return breakingNewses.size();
    }

    public ArrayList<BreakingNews> getBreakingNewses() {
        return breakingNewses;
    }

    public void setBreakingNewses(ArrayList<BreakingNews> breakingNewses) {
        this.breakingNewses = breakingNewses;
        notifyDataSetChanged();
    }

    public OnRecyclerItemClickListener getOnRecyclerItemClickListener() {
        return onRecyclerItemClickListener;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }
}
