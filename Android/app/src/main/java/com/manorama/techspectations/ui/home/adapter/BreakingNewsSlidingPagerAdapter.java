package com.manorama.techspectations.ui.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.ui.home.BreakingNewsFragment;
import com.manorama.techspectations.util.Constants;

import java.util.ArrayList;

/**
 * Created by Godwin Joseph on 10-11-2016 16:26 for Techspectations application.
 */

public class BreakingNewsSlidingPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<BreakingNews> breakingNewses = new ArrayList<>();

    public BreakingNewsSlidingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BreakingNews breakingNews = breakingNewses.get(position);
        BreakingNewsFragment fragment = BreakingNewsFragment.newInstance();
        Bundle bd = new Bundle();
        bd.putParcelable(Constants.IntentConstants.BREAKING_NEWS_OBJECT, breakingNews);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public int getCount() {
        return breakingNewses.size();
    }

    public ArrayList<BreakingNews> getBreakingNewses() {
        return breakingNewses;
    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }

    public void setBreakingNewses(ArrayList<BreakingNews> breakingNewses) {
        this.breakingNewses = breakingNewses;
        notifyDataSetChanged();
    }
}
