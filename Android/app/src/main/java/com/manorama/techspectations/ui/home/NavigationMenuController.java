package com.manorama.techspectations.ui.home;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.manorama.techspectations.R;
import com.manorama.techspectations.util.TechSpectationPreference;

/**
 * Created by Godwin Joseph on 11-11-2016 22:47 for Techspectations application.
 */

public class NavigationMenuController {
    public NavigationView createHeaderForNavigationView(NavigationView navigationView) {
        LayoutInflater inflater = (LayoutInflater) navigationView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mHeaderView = inflater.inflate(R.layout.nav_header_home, null);

        return setUpHeaderView(navigationView, mHeaderView);
    }

    private NavigationView setUpHeaderView(NavigationView navigationView, View headerView) {
        TextView tvDisplayName = (TextView) headerView.findViewById(R.id.tv_display_name);
        TextView tvUserName = (TextView) headerView.findViewById(R.id.tv_user_name);
        SimpleDraweeView sdvProfilePic = (SimpleDraweeView) headerView.findViewById(R.id.sdv_profile);

        String userName = TechSpectationPreference.getInstance().getStringPrefValue(TechSpectationPreference.USER_NAME);
        String displayName = TechSpectationPreference.getInstance().getStringPrefValue(TechSpectationPreference.USER_DISPLAY_NAME);
        String profilePicUri = TechSpectationPreference.getInstance().getStringPrefValue(TechSpectationPreference.USER_PROFILE_PIC_URI);

        if (displayName == null) {
            displayName = "Guest";
        }
        tvDisplayName.setText(displayName);
        if (profilePicUri != null) {
            Uri uri = Uri.parse(profilePicUri);
            sdvProfilePic.setImageURI(uri);
        }
        navigationView.addHeaderView(headerView);
        return navigationView;
    }
}
