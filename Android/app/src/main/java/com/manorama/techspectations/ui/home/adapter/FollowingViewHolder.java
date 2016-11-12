package com.manorama.techspectations.ui.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.manorama.techspectations.R;

/**
 * Created by Godwin Joseph on 12-11-2016 15:07 for Techspectations application.
 */

public class FollowingViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llFollowingItem;
    public TextView tvTitle;
    public SimpleDraweeView sdvFollwing;

    public FollowingViewHolder(View v) {
        super(v);
        llFollowingItem = (LinearLayout) v.findViewById(R.id.llfollwing_item);
        tvTitle = (TextView) v.findViewById(R.id.tv_follwing_header);
        sdvFollwing = (SimpleDraweeView) v.findViewById(R.id.sdv_following);
    }
}
