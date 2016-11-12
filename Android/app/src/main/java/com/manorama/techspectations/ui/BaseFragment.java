package com.manorama.techspectations.ui;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Godwin Joseph on 10-11-2016 16:28 for Techspectations application.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract void initializeWidgets(View v);

    protected abstract void registerListeners();
}
