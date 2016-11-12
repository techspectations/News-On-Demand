package com.manorama.techspectations.interfaces;

import com.manorama.techspectations.model.UserModel;

/**
 * Created by Albi on 11/10/2016.
 */

public interface SiginInteractorListener {

    void onSuccess(UserModel model);
    void onFailure(int errorCode, String errorMsg);
    void onAddLikesSuccess();
    void onAddLikesFailed(int errorCode, String errorMsg);
}
