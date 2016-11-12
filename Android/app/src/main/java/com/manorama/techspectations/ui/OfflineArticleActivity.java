package com.manorama.techspectations.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manorama.techspectations.R;
import com.manorama.techspectations.util.views.DividerItemDecoration;

import java.io.File;

/**
 * Created by Albi on 11/11/2016.
 */

public class OfflineArticleActivity extends BaseActivity {

    private static final String TAG = "OfflineArticleActivity";
    RecyclerView rvOfflineArticle;
    RecyclerViewAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_type);
        rvOfflineArticle = (RecyclerView) findViewById(R.id.rv_main);
        rvOfflineArticle.setHasFixedSize(true);
        rvOfflineArticle.setLayoutManager(new GridLayoutManager(OfflineArticleActivity.this, 2));
        int spanCount = 1;
        int spacing = 25;
        boolean includeEdge = true;
        rvOfflineArticle.addItemDecoration(new DividerItemDecoration(spanCount, spacing, includeEdge));
        mRecyclerAdapter = new RecyclerViewAdapter();
        rvOfflineArticle.setAdapter(mRecyclerAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //    setDataToRecyclerView();
    }

    @Override
    protected Context initializeContext() {
        return this;
    }

    @Override
    protected void initializeWidgets() {

        setupToolbar("News", true);
    }

    @Override
    protected void registerListeners() {

    }


    private void openPdf() {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/news/EmergingTechnology.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
        long[] numberOfDevices = {0, 0, 0, 0};

        @Override
        public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_books, parent, false);
            RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
            return rcv;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolders holder, final int position) {

            switch (position) {
                case 0:
                    holder.iv_newsBook.setImageResource(R.drawable.ic_tech);
                    holder.tv_newsBook.setText("Life Style");
                    break;
                case 1:
                    holder.iv_newsBook.setImageResource(R.drawable.ic_tech);
                    holder.tv_newsBook.setText("Life Style");
                    break;
                case 2:
                    holder.iv_newsBook.setImageResource(R.drawable.ic_tech);
                    holder.tv_newsBook.setText("Life Style");
                    break;

                case 3:
                    holder.iv_newsBook.setImageResource(R.drawable.ic_tech);
                    holder.tv_newsBook.setText("BLife Style");
                    break;

                case 4:
                    holder.iv_newsBook.setImageResource(R.drawable.ic_tech);
                    holder.tv_newsBook.setText("Life Style");
                    break;

                case 5:
                    holder.iv_newsBook.setImageResource(R.drawable.ic_tech);
                    holder.tv_newsBook.setText("BLife Style");
                    break;
            }
//            holder.ll_lifeStyle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return 6;
        }

    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView ll_lifeStyle;
        public TextView tv_newsBook;
        public ImageView iv_newsBook;


        public RecyclerViewHolders(View itemView) {
            super(itemView);
            ll_lifeStyle = (CardView) findViewById(R.id.cv_lifeStyle);
            tv_newsBook = (TextView) itemView.findViewById(R.id.tv_newsBook);
            iv_newsBook = (ImageView) itemView.findViewById(R.id.iv_newsBook);
            iv_newsBook.setOnClickListener(this);
            tv_newsBook.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            openPdf();
        }
    }

    private void setDataToRecyclerView() {


    }
}
