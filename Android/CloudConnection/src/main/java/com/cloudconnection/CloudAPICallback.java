package com.cloudconnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Godwin on 02-02-2016.
 */
public interface CloudAPICallback {
    public void onSuccess(JSONObject jsonObject);

    public void onSuccess(JSONArray jsonArray);

    public void onFailure(int errorCode, String errorMessage);
}
