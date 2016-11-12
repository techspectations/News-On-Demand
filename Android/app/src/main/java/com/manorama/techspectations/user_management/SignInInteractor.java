package com.manorama.techspectations.user_management;

import android.content.Context;

import com.cloudconnection.CloudAPICallback;
import com.cloudconnection.CloudConnectHttpMethod;
import com.manorama.techspectations.interfaces.SiginInteractorListener;
import com.manorama.techspectations.model.UserModel;
import com.manorama.techspectations.util.Common;
import com.manorama.techspectations.util.TechSpectationPreference;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Albi on 11/10/2016.
 */

public class SignInInteractor {

    Context mContext;
    SiginInteractorListener mListener;
    public SignInInteractor(Context context, SiginInteractorListener listener){

        this.mContext = context;
        this.mListener = listener;
    }

    public void addUserDetailsToServer(final UserModel model){

        CloudAPICallback apiCallback = new CloudAPICallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {

                processResponseFromServer(jsonObject,model);
                mListener.onSuccess(model);
            }

            @Override
            public void onSuccess(JSONArray jsonArray) {

            }

            @Override
            public void onFailure(int i, String s) {

                mListener.onFailure(i, s);
            }
        };

        CloudConnectHttpMethod httpMethod = new CloudConnectHttpMethod(mContext, apiCallback);

        JSONObject entity = new JSONObject();
        try {
            entity.put("displayName", model.getDisplayName());
            entity.put("emailAddress", model.getEmailAddress());
            entity.put("age", model.getAge());
            entity.put("socialNetworkName", model.getSocialNetworkName());
            entity.put("socialNetworkId", model.getSocialNetworkId());
            entity.put("socialNetworkToken", model.getSocialNetworkToken());
            entity.put("gender", model.getGender());
            entity.put("facebookLocationId", model.getFacebookLocationId());
            entity.put("location", model.getLocation());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        HashMap<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json");

        String url = Common.AppConstants.BASE_URL + "users";
        httpMethod.setHeaderMap(header);
        httpMethod.setEntityString(entity.toString());
        httpMethod.setUrl(url);
        httpMethod.setRequestType(CloudConnectHttpMethod.POST_METHOD);
        httpMethod.execute();
    }

    public void addUserLikesToServer(final JSONArray jArray){

        CloudAPICallback apiCallback = new CloudAPICallback() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                //processResponseFromServer(jsonObject, use);
               mListener.onAddLikesSuccess();
            }

            @Override
            public void onSuccess(JSONArray jsonArray) {

            }

            @Override
            public void onFailure(int i, String s) {

                mListener.onAddLikesFailed(i, s);
            }
        };

        CloudConnectHttpMethod httpMethod = new CloudConnectHttpMethod(mContext, apiCallback);
        HashMap<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json");
        TechSpectationPreference pref = TechSpectationPreference.getInstance();

        long userId = pref.getLongPrefValue(Common.PreferenceStaticValues.USER_ID);
        String url = Common.AppConstants.BASE_URL + "users/"+userId+"/likes";
        httpMethod.setHeaderMap(header);
        httpMethod.setEntityString(jArray.toString());
        httpMethod.setUrl(url);
        httpMethod.setRequestType(CloudConnectHttpMethod.POST_METHOD);
        httpMethod.execute();
    }

    private void processResponseFromServer(JSONObject jsonObject,UserModel model) {

        if(jsonObject != null){

            TechSpectationPreference pref = TechSpectationPreference.getInstance();
            pref.setStringPrefValue(Common.PreferenceStaticValues.SERVER_TOKEN, jsonObject.optString("apiRequestToken"));
            pref.setLongPrefValue(Common.PreferenceStaticValues.USER_ID, jsonObject.optInt("id"));
            pref.setStringPrefValue(Common.PreferenceStaticValues.PROFILE_PIC_URL, model.getProfilePicUrl());
            pref.setLongPrefValue(Common.PreferenceStaticValues.USER_ID,jsonObject.optInt("id"));
            pref.setStringPrefValue(Common.PreferenceStaticValues.USER_DISPLAY_NAME, jsonObject.optString("displayName"));
            pref.setStringPrefValue(Common.PreferenceStaticValues.USER_EMAIL, jsonObject.optString("emailAddress"));
        }
    }
}
