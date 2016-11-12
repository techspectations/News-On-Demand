package com.manorama.techspectations.ui.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.model.News;
import com.manorama.techspectations.ui.home.BreakingNewsFragment;
import com.manorama.techspectations.ui.home.NewsFragment;
import com.manorama.techspectations.util.Constants;

import java.util.ArrayList;

/**
 * Created by Godwin Joseph on 11-11-2016 12:20 for Techspectations application.
 */

public class NewsSlidingPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<News> newses = new ArrayList<>();

    public NewsSlidingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        News breakingNews = newses.get(position);
        NewsFragment fragment = NewsFragment.newInstance();
        Bundle bd = new Bundle();
        bd.putParcelable(Constants.IntentConstants.NEWS_OBJECT, breakingNews);
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public int getCount() {
        return newses.size();
    }

    public ArrayList<News> getNewses() {
        return newses;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
        notifyDataSetChanged();
    }
}
