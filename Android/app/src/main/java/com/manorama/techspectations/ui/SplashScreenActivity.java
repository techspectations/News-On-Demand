package com.manorama.techspectations.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.SiginInteractorListener;
import com.manorama.techspectations.model.UserModel;
import com.manorama.techspectations.ui.home.HomeActivity;
import com.manorama.techspectations.user_management.SignInInteractor;
import com.manorama.techspectations.util.Common;
import com.manorama.techspectations.util.MyNetworkUtility;
import com.manorama.techspectations.util.TechSpectationPreference;
import com.manorama.techspectations.util.views.DisplayUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SplashScreenActivity extends BaseActivity implements SiginInteractorListener, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{
    VideoView videoView;
    LinearLayout llIntro, llTitle;
    private CallbackManager callbackManager;
    ImageView iv_facebook, iv_google;
    TextView tvSkip;
    LoginButton loginButton;
    String FACEBOOK_USER_ID = "id", FACEBOOK_USER_NAME = "name", FACEBOOK_USER_FIRST_NAME = "first_name", FACEBOOK_USER_LAST_NAME = "last_name",
            FACEBOOK_USER_GENDER = "gender", FACEBOOK_USER_LOCATION = "location", FACEBOOK_USER_BIRTH_DATE = "birthday", FACEBOOK_USER_EMAIL = "email";
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    Context mContext;

    private GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 1001;
    private ArrayList<String> mPermissions;
    private static final int REQUEST_CODE=100;

    final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPermissions();
        initializeFacebookSdk();
        setUpGoogleSignUp();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showBreakingNews = false;
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        findViewById(R.id.rl_main).bringToFront();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animate();
            }
        }, 2000);
    }

    @Override
    protected Context initializeContext() {
        return this;
    }

    @Override
    protected void initializeWidgets() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        tvSkip = (TextView) findViewById(R.id.tv_skip);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends", "user_location",
                "user_likes"));

        videoView = (VideoView) findViewById(R.id.video_view);

        llIntro = (LinearLayout) findViewById(R.id.ll_intro);
        llTitle = (LinearLayout) findViewById(R.id.ll_title);

        iv_facebook = (ImageView) findViewById(R.id.iv_facebook);
        iv_google = (ImageView) findViewById(R.id.iv_google);

        iv_facebook.bringToFront();
        iv_google.bringToFront();

        tvSkip.setOnClickListener(this);
    }

    @Override
    protected void registerListeners() {
        loginButton.registerCallback(callbackManager, callback);

        iv_facebook.setOnClickListener(this);
        iv_google.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
    }

    private void playBackGroundVideo() {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        //specify the location of media file
        final Uri mVideoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro_6);

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(null);
        videoView.setVideoURI(mVideoUri);
        videoView.start();
        mediaController.hide();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    private void initializeFacebookSdk() {
        FacebookSdk.sdkInitialize(getApplicationContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                //displayMessage(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    private void animate() {

        RelativeLayout root = (RelativeLayout) findViewById(R.id.activity_splash_screen);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        llTitle.getLocationOnScreen(originalPos);

        int xDest = dm.widthPixels / 2;
        xDest -= (llTitle.getMeasuredWidth() / 2);
        int yDest = dm.heightPixels / 2 - (llTitle.getMeasuredHeight() / 2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation(0, xDest - originalPos[0], 0, yDest - originalPos[1]);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        anim.setInterpolator(new OvershootInterpolator());
        llTitle.startAnimation(anim);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
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
        loadPermissions(mPermissions);
        Profile profile = Profile.getCurrentProfile();
        //   displayMessage(profile);
        playBackGroundVideo();
    }

    protected void loadPermissions(ArrayList<String> mPermissions) {

        mPermissions = getUnGrantedPermissions(mPermissions);
        if(mPermissions != null && mPermissions.size() > 0)
            ActivityCompat.requestPermissions(this, mPermissions.toArray(new String[mPermissions.size()]), REQUEST_CODE);
    }

    public void setPermissions(){

        mPermissions = new ArrayList<>();
        mPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        mPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        mPermissions.add(Manifest.permission.READ_CALENDAR);
        mPermissions.add(Manifest.permission.WRITE_CALENDAR);
    }

    private ArrayList<String> getUnGrantedPermissions(ArrayList<String> mPermissions){

        ArrayList<String>  unGrantedPer = new ArrayList<>();
        for(String perm : mPermissions) {

            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {

                unGrantedPer.add(perm);
            }
        }
        return unGrantedPer;
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
                                JSONObject data = response.getJSONObject();
                                if (data.has("picture")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                    model.setProfilePicUrl(profilePicUrl);
                                    //     Bitmap profilePic = BitmapFactory.decodeStream(profilePicUrl.openConnection().getInputStream());// set profilePic bitmap to imageview
                                }

                                model.setDisplayName(object.getString(FACEBOOK_USER_NAME));
                                model.setEmailAddress(object.getString(FACEBOOK_USER_EMAIL));
                                String birth_date = object.getString(FACEBOOK_USER_BIRTH_DATE);
                                model.setAge(getAge(birth_date));
                                model.setSocialNetworkName(Common.SocialNetworks.FB);
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
            parameters.putString("fields", "id,name,email,picture.type(large),link,birthday,first_name,last_name,gender,location,locale,timezone,updated_time,verified");
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
        SignInInteractor interactor = new SignInInteractor(mContext, this);
        interactor.addUserDetailsToServer(model);
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

//    private void displayMessage(Profile profile) {
//        if (profile != null) {
//            textView.setText(profile.getName());
//        }
//    }

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
        SignInInteractor interactor = new SignInInteractor(mContext, this);
        interactor.addUserLikesToServer(jArray);
    }

    @Override
    public void onSuccess(UserModel model) {

        if (model.getSocialNetworkName().equals(Common.SocialNetworks.FB)) {

            getLikedPageInfo(model.getSocialNetworkId());
        }else{

            Toast.makeText(mContext, "Sign In Success " + model.getDisplayName(), Toast.LENGTH_SHORT).show();
            goToHome();
        }

    }

    @Override
    public void onFailure(int errorCode, String errorMsg) {

        Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_facebook:
                DisplayUtils.showToast("Clicked facebook");
                loginButton.performClick();
                break;
            case R.id.iv_google:
                if(MyNetworkUtility.checkInternetConnection(this)) {
                    signInUsingGoogleAccount();
                }else{
                    Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_skip:
                DisplayUtils.showToast("Clicked skip");
                break;
        }
    }

    @Override
    public void onAddLikesSuccess() {

        goToHome();
    }

    @Override
    public void onAddLikesFailed(int errorCode, String errorMsg) {

        goToHome();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
    }

    private void setUpGoogleSignUp(){

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Plus.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void signInUsingGoogleAccount() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
            Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            postUserDataToServer(acct, person);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
        }
    }


    private String getLocationName(){

        String location = null;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean enabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

// check if enabled and if not send user to the GSP settings
// Better solution would be to display a dialog and suggesting to
// go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        String provider = locationManager.getBestProvider(new Criteria(), true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return location;
        }
        Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if(null!=locations && null!=providerList && providerList.size()>0) {
            double longitude = locations.getLongitude();
            double latitude = locations.getLatitude();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(addresses != null) {
                location = addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getLocality()
                        + ", " + addresses.get(0).getSubAdminArea();

            }
        }

        return location;
    }

    private void postUserDataToServer(GoogleSignInAccount acct, Person person){

        String url = person.getImage().getUrl();
        UserModel userModel = new UserModel();
        userModel.setSocialNetworkName(Common.SocialNetworks.GOOGLE);
        userModel.setGender(person.getGender() == 0 ? "Male" : "Female");
        userModel.setSocialNetworkToken(Common.AppConstants.GOOGLE_API_KEY);
        userModel.setSocialNetworkId(acct.getId());
        userModel.setDisplayName(acct.getDisplayName());
        userModel.setEmailAddress(acct.getEmail());
        userModel.setLocation(getLocationName());

        SignInInteractor interactor = new SignInInteractor(this, this);
        interactor.addUserDetailsToServer(userModel);

        TechSpectationPreference.getInstance().setStringPrefValue(Common.PreferenceStaticValues.PROFILE_PIC_URL, url);
    }

    private void goToHome(){

        startActivity(new Intent(this, HomeActivity.class));
    }
}
