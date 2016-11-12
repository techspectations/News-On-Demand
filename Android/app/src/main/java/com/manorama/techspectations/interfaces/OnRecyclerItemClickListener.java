package com.manorama.techspectations.interfaces;

import android.view.View;

/**
 * Created by Godwin Joseph on 10-11-2016 18:41 for Techspectations application.
 */

public interface OnRecyclerItemClickListener {
    public void onItemClicked(Object object, View view, int position);

    public void onItemLongClicked(Object object, View view, int position);
}
