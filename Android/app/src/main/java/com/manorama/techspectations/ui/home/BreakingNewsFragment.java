package com.manorama.techspectations.ui.home;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.manorama.techspectations.model.BreakingNews;
import com.manorama.techspectations.ui.BaseFragment;
import com.manorama.techspectations.util.Constants;
import com.manorama.techspectations.util.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreakingNewsFragment extends BaseFragment {

    private static final String TAG = "BreakingNewsFragment";
    private static BreakingNews breakingNews;
    private TextView tvBreakingNews;
    private SimpleDraweeView simpleDraweeView;

    public BreakingNewsFragment() {
        // Required empty public constructor
    }

    public static BreakingNewsFragment newInstance() {

        Bundle args = new Bundle();

        BreakingNewsFragment fragment = new BreakingNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        breakingNews = getArguments().getParcelable(Constants.IntentConstants.BREAKING_NEWS_OBJECT);
        return inflater.inflate(R.layout.fragment_breaking_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeWidgets(view);
        registerListeners();
    }

    @Override
    protected void initializeWidgets(View v) {
        tvBreakingNews = (TextView) v.findViewById(R.id.tv_news_header);
        simpleDraweeView = (SimpleDraweeView) v.findViewById(R.id.fresco_breaking);

        tvBreakingNews.setText(breakingNews.getNewsHeading());
        if (breakingNews.getNewsMobileImageUrl() != null) {
            Uri imageUri = Uri.parse(breakingNews.getNewsMobileImageUrl());
            getBitmap(imageUri);

            simpleDraweeView.setImageURI(imageUri);
        }
    }

    @Override
    protected void registerListeners() {

    }

    private void getBitmap(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setRequestPriority(Priority.HIGH)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();

        DataSource<CloseableReference<CloseableImage>> dataSource =
                imagePipeline.fetchDecodedImage(imageRequest, BreakingNewsFragment.this);

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
                   // setupHeadingColor(bitmap);
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

    private void setupHeadingColor(Bitmap bitmap) {
        Palette palette = Palette.from(bitmap).generate();

        int defaultColor = 0x000000;
        int vibrant = palette.getVibrantColor(defaultColor);
        int vibrantLight = palette.getLightVibrantColor(defaultColor);
        int vibrantDark = palette.getDarkVibrantColor(defaultColor);
        int muted = palette.getMutedColor(defaultColor);
        int mutedLight = palette.getLightMutedColor(defaultColor);
        int mutedDark = palette.getDarkMutedColor(defaultColor);

       // tvBreakingNews.setTextColor(vibrantDark);
    }
}
