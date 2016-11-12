package com.manorama.techspectations.google;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.manorama.techspectations.R;
import com.manorama.techspectations.interfaces.SiginInteractorListener;
import com.manorama.techspectations.model.UserModel;
import com.manorama.techspectations.user_management.SignInInteractor;
import com.manorama.techspectations.util.Common;
import com.manorama.techspectations.util.MyNetworkUtility;
import com.manorama.techspectations.util.TechSpectationPreference;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Albi on 11/9/2016.
 */

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, SiginInteractorListener {

    // Declaring private global variables
    private static final String TAG = "SignInActivity";
    private GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 1001;
    private ArrayList<String> mPermissions;
    private static final int REQUEST_CODE=100;
    /**
     * Called when the activity is started
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        setPermissions();
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
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPermissions(mPermissions);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void initView() {
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //getPeopleDEtails();
        if(MyNetworkUtility.checkInternetConnection(this)) {
            signIn();
        }else{
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
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
        List<String>  providerList = locationManager.getAllProviders();
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
        userModel.setSocialNetworkName(acct.getGivenName());
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

    @Override
    public void onSuccess(UserModel model) {

        Toast.makeText(this, "Signed in success: " + model.getDisplayName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(int errorCode, String errorMsg) {

        Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddLikesSuccess() {


    }

    @Override
    public void onAddLikesFailed(int errorCode, String errorMsg) {

    }
}
