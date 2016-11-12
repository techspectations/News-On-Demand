package com.manorama.techspectations.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.NewsInteractorListener;
import com.manorama.techspectations.interfaces.OnRecyclerItemClickListener;
import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.new_management.NewsInteractor;
import com.manorama.techspectations.ui.BaseActivity;
import com.manorama.techspectations.ui.home.adapter.BreakingNewsSlidingPagerAdapter;
import com.manorama.techspectations.ui.home.adapter.NewsRecyclerAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";
    private static final int SECOND = 1000;
    /**
     * Declaring views
     */
    ViewPager vpBreakingNews;
    CircleIndicator ciBreakingNews;
    RecyclerView rvNewsList;
    /**
     * Custom class declaration
     */
    BreakingNewsSlidingPagerAdapter mSectionPagerAdapter;
    NewsRecyclerAdapter mRecyclerAdapter;
    Timer mTimer;
    NavigationMenuController menuController;
    CollapsingToolbarLayout ctl_home;
    NewsInteractor newsInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar("Home", true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menuController = new NavigationMenuController();
        navigationView = menuController.createHeaderForNavigationView(navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected Context initializeContext() {
        return this;
    }

    @Override
    protected void initializeWidgets() {

        vpBreakingNews = (ViewPager) findViewById(R.id.vp_breaking_news);
        ciBreakingNews = (CircleIndicator) findViewById(R.id.ci_breaking_news);
        rvNewsList = (RecyclerView) findViewById(R.id.rv_news_list);

        ctl_home = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        rvNewsList.setLayoutManager(new StaggeredGridLayoutManager(1, 1));

        mSectionPagerAdapter = new BreakingNewsSlidingPagerAdapter(getSupportFragmentManager());
        vpBreakingNews.setAdapter(mSectionPagerAdapter);

        ciBreakingNews.setViewPager(vpBreakingNews);
        mSectionPagerAdapter.registerDataSetObserver(ciBreakingNews.getDataSetObserver());

        mSectionPagerAdapter.setBreakingNewses(getDummyNews());

        mRecyclerAdapter = new NewsRecyclerAdapter();
        rvNewsList.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setBreakingNewses(getDummyNews());


        toolbarTextAppernce();
    }

    private void toolbarTextAppernce() {
        ctl_home.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        ctl_home.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

    @Override
    protected void registerListeners() {
        mRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClicked(Object object, View view, int position) {
                startActivity(new Intent(mContext, NewsActivity.class));
            }

            @Override
            public void onItemLongClicked(Object object, View view, int position) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startTimer() {
        stopTimer();
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                changePage();
            }
        };
        mTimer.schedule(task, 5 * SECOND, 5 * SECOND);
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void changePage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int totalCount = mSectionPagerAdapter.getCount();
                int currentItem = vpBreakingNews.getCurrentItem();
                if (vpBreakingNews.getCurrentItem() == totalCount - 1) {
                    vpBreakingNews.setCurrentItem(0);
                } else {
                    vpBreakingNews.setCurrentItem(currentItem + 1);
                }
            }
        });
    }

    private ArrayList<BreakingNews> getDummyNews() {
        ArrayList<BreakingNews> newses = new ArrayList<>();
//        BreakingNews news = new BreakingNews();
//        news.setNewsHeading("Modi Banned 1000 and 500");
//        news.setNewsMobileImageUrl("http://plusquotes.com/images/quotes-img/cool-pictures-24.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("US Election 2016: A survivor's guide to unexpected voting results");
//        news.setNewsMobileImageUrl("https://newevolutiondesigns.com/images/freebies/cool-wallpaper-1.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("Brad Pitt 'abusive behaviour investigation closed'");
//        news.setNewsMobileImageUrl("http://dukeo.com/media/low-quality-blog-content.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("Donald Trump election win sparks protests in US cities'");
//        news.setNewsMobileImageUrl("http://dukeo.com/media/low-quality-blog-content.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("US Election 2016: A survivor's guide to unexpected voting results");
//        news.setNewsMobileImageUrl("https://newevolutiondesigns.com/images/freebies/cool-wallpaper-1.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("Brad Pitt 'abusive behaviour investigation closed'");
//        news.setNewsMobileImageUrl("http://dukeo.com/media/low-quality-blog-content.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("Donald Trump election win sparks protests in US cities'");
//        news.setNewsMobileImageUrl("http://dukeo.com/media/low-quality-blog-content.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("US Election 2016: A survivor's guide to unexpected voting results");
//        news.setNewsMobileImageUrl("https://newevolutiondesigns.com/images/freebies/cool-wallpaper-1.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("Brad Pitt 'abusive behaviour investigation closed'");
//        news.setNewsMobileImageUrl("http://dukeo.com/media/low-quality-blog-content.jpg");
//        newses.add(news);
//
//        news = new BreakingNews();
//        news.setNewsHeading("Donald Trump election win sparks protests in US cities'");
//        news.setNewsMobileImageUrl("http://dukeo.com/media/low-quality-blog-content.jpg");
//        newses.add(news);

        NewsInteractorListener listener = new NewsInteractorListener() {
            @Override
            public void onGetBreakingNewsSuccess(ArrayList<BreakingNews> breakingNewsList) {

            }

            @Override
            public void onGetBreakingNewsFailed(int errorCode, String errorMsg) {

            }
        };

        newsInteractor = new NewsInteractor(this, listener);
        newsInteractor.getAllNewBasedOnUserData();

        return newses;
    }
}
