package com.manorama.techspectations.ui.home;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.NewsInteractorListener;
import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.model.News;
import com.manorama.techspectations.new_management.NewsInteractor;
import com.manorama.techspectations.ui.BaseFragment;
import com.manorama.techspectations.util.Constants;
import com.manorama.techspectations.util.DisplayInfo;
import com.manorama.techspectations.util.Logger;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "NewsFragment";
    News news;
    CollapsingToolbarLayout ctl;
    Toolbar toolbar;
    SimpleDraweeView sdvThumbNail;
    TextView tv_news_content;
    TextView tv_news_header;
    FloatingActionButton fabDownload, fabFollow;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        news = getArguments().getParcelable(Constants.IntentConstants.NEWS_OBJECT);
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeWidgets(view);
        registerListeners();
//        if (news != null)
//            getNewsDetailsFromServer(news.getNewsArticleId());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (news != null)
                        getNewsDetailsFromServer(news.getNewsArticleId());
                }
            }, 500);

        }
    }

    private void getNewsDetailsFromServer(String newsArticleId) {

        NewsInteractorListener listener = new NewsInteractorListener() {
            @Override
            public void onGetBreakingNewsSuccess(ArrayList<BreakingNews> breakingNewsList) {

            }

            @Override
            public void onGetBreakingNewsFailed(int errorCode, String errorMsg) {

            }

            @Override
            public void onGetNewsDetailSuccess(News newsDetails) {

                DisplayInfo.dismissLoader(getActivity());
                if (newsDetails != null) {

                    NewsFragment.this.news = newsDetails;
                    updateView();
                }
            }

            @Override
            public void onGetNewsDetailFailed(int errorCode, String errorMsg) {
                DisplayInfo.dismissLoader(getActivity());
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        };

        if (TextUtils.isEmpty(news.getNews())) {
            DisplayInfo.showLoader(getActivity(), getString(R.string.pd_please_wait));
            NewsInteractor interactor = new NewsInteractor(getActivity(), listener);
            interactor.getDetailsOfNews(newsArticleId);
        } else {

            updateView();
        }
    }

    @Override
    protected void initializeWidgets(View v) {
        ctl = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar);

        fabDownload = (FloatingActionButton) v.findViewById(R.id.download);
        fabFollow = (FloatingActionButton) v.findViewById(R.id.follow);
        tv_news_content = (TextView) v.findViewById(R.id.tv_news_content);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        sdvThumbNail = (SimpleDraweeView) v.findViewById(R.id.sdv_news_thumbnail);
        tv_news_header = (TextView) v.findViewById(R.id.tv_news_header);
        toolbar.setNavigationIcon(R.drawable.nav_back);
        processImageRequest();
        toolbarTextAppernce();
    }

    private void updateView() {

        if (toolbar != null)
            toolbar.setTitle(news.getNewsHeading());
        if (tv_news_content != null)
            tv_news_content.setText(Html.fromHtml(news.getNews()));

        if(tv_news_header != null)
            tv_news_header.setText(news.getNewsHeading());

    }

    private void toolbarTextAppernce() {
        ctl.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        ctl.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

    @Override
    protected void registerListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        fabFollow.setOnClickListener(this);
        fabDownload.setOnClickListener(this);
    }

    private void processImageRequest() {
        Uri imageUri = Uri.parse(news.getNewsMobileImageUrl());
        sdvThumbNail.setImageURI(imageUri);
    }

    private void showImage(Bitmap thumbNail) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(getActivity());
        ImageView image = new ImageView(getActivity());
        imageDialog.setView(image);
        image.setImageBitmap(thumbNail);
        imageDialog.setCancelable(true);
        imageDialog.create().show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sdv_news_thumbnail:
                Uri imageUri = Uri.parse(news.getNewsMobileImageUrl());
                getBitmap(imageUri);
                break;
            case R.id.download:
                DisplayInfo.showToast(getActivity(), getString(R.string.msg_downloading));
                break;
            case R.id.follow:
                DisplayInfo.showToast(getActivity(), getString(R.string.msg_following));
                break;
        }
    }

    private void getBitmap(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setRequestPriority(Priority.HIGH)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();

        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, getActivity());

        try {
            dataSource.subscribe(new BaseBitmapDataSubscriber() {
                @Override
                public void onNewResultImpl(@Nullable Bitmap bitmap) {
                    if (bitmap == null) {
                        Logger.d(TAG, "Bitmap data source returned success, but bitmap null.");
                        return;
                    }
                    // The bitmap provided to this method is only guaranteed to be around
                    // for the lifespan of this method. The image pipeline frees the
                    // bitmap's memory after this method has completed.
                    //
                    // This is fine when passing the bitmap to a system process as
                    // Android automatically creates a copy.
                    //
                    // If you need to keep the bitmap around, look into using a
                    // BaseDataSubscriber instead of a BaseBitmapDataSubscriber.
                    showImage(bitmap);
                }

                @Override
                public void onFailureImpl(DataSource dataSource) {
                    // No cleanup required here
                }
            }, CallerThreadExecutor.getInstance());
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }
}
