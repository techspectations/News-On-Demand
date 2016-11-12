package com.manorama.techspectations.ui.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.manorama.techspectations.R;

/**
 * Created by Godwin Joseph on 10-11-2016 18:34 for Techspectations application.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public TextView tvHeading, tvProvider, tvUpdatedTime;
    public SimpleDraweeView sdvThumbNail;
    public LinearLayout llNewsItem;

    public NewsViewHolder(View v) {
        super(v);
        tvHeading = (TextView) v.findViewById(R.id.tv_news_header);
        tvProvider = (TextView) v.findViewById(R.id.tv_news_provider);
        tvUpdatedTime = (TextView) v.findViewById(R.id.tv_news_updated_time);

        sdvThumbNail = (SimpleDraweeView) v.findViewById(R.id.sdv_news_thumbnail);

        llNewsItem = (LinearLayout) v.findViewById(R.id.ll_news_item);
    }
}
