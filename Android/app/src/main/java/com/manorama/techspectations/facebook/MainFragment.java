package com.manorama.techspectations.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.SiginInteractorListener;
import com.manorama.techspectations.model.UserModel;
import com.manorama.techspectations.user_management.SignInInteractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements SiginInteractorListener {

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    String FACEBOOK_USER_ID = "id", FACEBOOK_USER_NAME = "name", FACEBOOK_USER_FIRST_NAME = "first_name", FACEBOOK_USER_LAST_NAME = "last_name",
            FACEBOOK_USER_GENDER = "gender", FACEBOOK_USER_LOCATION = "location", FACEBOOK_USER_BIRTH_DATE = "birthday", FACEBOOK_USER_EMAIL = "email";

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends", "user_location",
                "user_likes"));
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        //displayMessage(profile);
    }

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            final AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            try {
                                UserModel model = new UserModel();
                                model.setDisplayName(object.getString(FACEBOOK_USER_NAME));
                                model.setEmailAddress(object.getString(FACEBOOK_USER_EMAIL));
                                String birth_date = object.getString(FACEBOOK_USER_BIRTH_DATE);
                                model.setAge(getAge(birth_date));
                                model.setSocialNetworkName("Facebook");
                                model.setSocialNetworkId(object.getString(FACEBOOK_USER_ID));
                                model.setSocialNetworkToken(accessToken.getToken());
                                model.setGender(object.getString(FACEBOOK_USER_GENDER));
                                JSONObject object2 = object.getJSONObject(FACEBOOK_USER_LOCATION);
                                model.setFacebookLocationId(object2.getString(FACEBOOK_USER_ID));
                                model.setLocation(object2.getString(FACEBOOK_USER_NAME));
                                signUpWithFacebook(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,link,birthday,first_name,last_name,gender,location,locale,timezone,updated_time,verified");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException e) {
        }
    };

    private void signUpWithFacebook(UserModel model) {
        SignInInteractor interactor = new SignInInteractor(getActivity(), this);
        interactor.addUserDetailsToServer(model);
    }

    @Override
    public void onSuccess(UserModel model) {
        getLikedPageInfo(model.getSocialNetworkId());
    }

    @Override
    public void onFailure(int errorCode, String errorMsg) {
    }

    @Override
    public void onAddLikesSuccess() {

    }

    @Override
    public void onAddLikesFailed(int errorCode, String errorMsg) {

    }

    private int getAge(String birthda) {
        String[] items1 = birthda.split("/");
        String month1 = items1[0];
        String date1 = items1[1];
        String year1 = items1[2];
        int day = Integer.parseInt(date1);
        int month = Integer.parseInt(month1);
        int year = Integer.parseInt(year1);
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        return age;
    }

    private void getLikedPageInfo(String id) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + id + "/likes",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        JSONObject responseObject = response.getJSONObject();
                        if (responseObject != null) {
                            JSONArray dataArray = responseObject.optJSONArray("data");
                            if (dataArray != null) {
                                JSONArray jArray = new JSONArray();
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject userObject = dataArray.optJSONObject(i);
                                    JSONObject jObject = new JSONObject();
                                    try {
                                        jObject.put("likeItem", userObject.optString("name"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jArray.put(jObject);
                                }
                                proceessApiForFacebookLikes(jArray);
                            }
                        }
                    }
                }
        ).executeAsync();
    }

    private void proceessApiForFacebookLikes(JSONArray jArray) {
        SignInInteractor interactor = new SignInInteractor(getActivity(), this);
        interactor.addUserLikesToServer(jArray);
    }
}
