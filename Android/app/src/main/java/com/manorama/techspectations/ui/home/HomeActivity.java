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
import com.manorama.techspectations.ads.VideoViewActivity;
import com.manorama.techspectations.database.manager.DatabaseManager;
import com.manorama.techspectations.interfaces.NewsInteractorListener;
import com.manorama.techspectations.interfaces.OnRecyclerItemClickListener;
import com.manorama.techspectations.interfaces.OnUiUpdatedListener;
import com.manorama.techspectations.interfaces.SiginInteractorListener;
import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.model.News;
import com.manorama.techspectations.model.UserModel;
import com.manorama.techspectations.new_management.NewsInteractor;
import com.manorama.techspectations.notification_management.NotificationCloudInteractor;
import com.manorama.techspectations.ui.BaseActivity;
import com.manorama.techspectations.ui.home.adapter.BreakingNewsSlidingPagerAdapter;
import com.manorama.techspectations.ui.home.adapter.NewsRecyclerAdapter;
import com.manorama.techspectations.user_management.SignInInteractor;
import com.manorama.techspectations.util.CalenderEvents;
import com.manorama.techspectations.util.Common;
import com.manorama.techspectations.util.Constants;
import com.manorama.techspectations.util.TechSpectationPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends BaseActivity implements SiginInteractorListener, NavigationView.OnNavigationItemSelectedListener, OnUiUpdatedListener {

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

    int count = 1;

    public BreakingNews getCurrentNews() {
        return currentNews;
    }

    public void setCurrentNews(News currentNews) {
        this.currentNews = currentNews;
    }

    BreakingNews currentNews;

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
        syncUserCalenderEvents();
        new NotificationCloudInteractor(this).syncFcmToken();
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

//        mSectionPagerAdapter.setBreakingNewses(getDummyNews());

        mRecyclerAdapter = new NewsRecyclerAdapter();
        rvNewsList.setAdapter(mRecyclerAdapter);

//        mRecyclerAdapter.setBreakingNewses(getDummyNews());


        toolbarTextAppernce();
    }

    private void syncUserCalenderEvents() {

        ArrayList<String> eventNames = CalenderEvents.readCalendarEvent(this);

        ArrayList<String> eventStartTime = CalenderEvents.startDates;
        TechSpectationPreference pref = TechSpectationPreference.getInstance();
        String userId = pref.getStringPrefValue(Common.PreferenceStaticValues.USER_ID);


        JSONArray jArray = new JSONArray();
        for (int i = 0; i < eventNames.size(); i++) {

            JSONObject jObject = new JSONObject();
            try {
                jObject.put("id", 0);
                jObject.put("userId", userId);
                jObject.put("event", eventNames.get(i));
                jObject.put("date", eventStartTime.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jArray.put(jObject);
        }
        proceessApiForEvents(jArray);

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
                BreakingNews news = (BreakingNews) object;
                String articleId = news.getNewsArticleId();
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra(Constants.IntentConstants.NEWS_POSITION, position);
                intent.putExtra(Constants.IntentConstants.ARTICLE_ID, articleId);
                startActivity(intent);
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
        setOnUiUpdatedListener(this);
        updateNewsFromDatabase();
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
        switch (id) {
            case R.id.nav_travel:
                startActivity(new Intent(mContext, FollowingListActivity.class));
                break;
        }
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
                count++;
                changePage();
                if (count % 9 == 0) {
                    startActivity(new Intent(mContext, VideoViewActivity.class));
                    count = 1;
                }
            }
        };
        mTimer.schedule(task, 5 * SECOND, 5 * SECOND);
    }

    private void stopTimer() {
        count = 1;
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


    private void proceessApiForEvents(JSONArray jArray) {
        SignInInteractor interactor = new SignInInteractor(mContext, this);
        interactor.addUserEventsToServer(jArray);
    }

    @Override
    public void onSuccess(UserModel model) {

    }

    @Override
    public void onFailure(int errorCode, String errorMsg) {

    }

    @Override
    public void onAddLikesSuccess() {

    }

    @Override
    public void onAddLikesFailed(int errorCode, String errorMsg) {

    }

    @Override
    public void onUiUpdated() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateNewsFromDatabase();
            }
        });
    }

    private void updateNewsFromDatabase() {
        ArrayList<BreakingNews> newsFromDb = DatabaseManager.getInstance().getBreakingNews();
        ArrayList<BreakingNews> breakingNewses;
        if(newsFromDb.size() > 10){
            breakingNewses = new ArrayList<>(newsFromDb.subList(0, 10));

        }else{

            breakingNewses = newsFromDb;
        }

        if(breakingNewses != null && breakingNewses.size() > 0){

            currentNews = breakingNewses.get(0);
        }
        mSectionPagerAdapter.setBreakingNewses(breakingNewses);
        mRecyclerAdapter.setBreakingNewses(newsFromDb);
    }
}
