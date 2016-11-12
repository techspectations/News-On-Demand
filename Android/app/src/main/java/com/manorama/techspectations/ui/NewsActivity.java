package com.manorama.techspectations.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.manorama.techspectations.R;

import java.io.File;

/**
 * Created by Albi on 11/11/2016.
 */

public class NewsActivity extends BaseActivity{

    LinearLayout ll_lifeStyle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_type);

    }

    @Override
    protected Context initializeContext() {
        return this;
    }

    @Override
    protected void initializeWidgets() {

        setupToolbar("News", true);
        ll_lifeStyle = (LinearLayout) findViewById(R.id.ll_lifeStyle);
    }

    @Override
    protected void registerListeners() {

        ll_lifeStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPdf();
            }
        });
    }

    private void openPdf() {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/news/EmergingTechnology.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
