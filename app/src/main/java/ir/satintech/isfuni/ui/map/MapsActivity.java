package ir.satintech.isfuni.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.satintech.isfuni.R;
import ir.satintech.isfuni.data.network.model.Input_Google_Direction;
import ir.satintech.isfuni.ui.base.BaseActivity;
import ir.satintech.isfuni.utils.AppConstants;
import ir.satintech.isfuni.utils.AppLogger;
import ir.satintech.isfuni.utils.GooglePlayServices;

public class MapsActivity extends BaseActivity implements MapsMvpView, OnMapReadyCallback
        , GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private GoogleMap mMap;
    double lat;
    double log;
    boolean routing;

    @Inject
    MapsMvpPresenter<MapsMvpView> mPresenter;

    GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    ///
    Location current_location = null;

    public static Intent getStartIntent(Context context, double lat, double log, boolean routing) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("log", log);
        intent.putExtra("routing", routing);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        GooglePlayServices.isGooglePlayServicesAvailableByFinish(MapsActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });


        routing = getIntent().getExtras().getBoolean("routing");

        if (routing) {

            lat = getIntent().getExtras().getDouble("lat");
            log = getIntent().getExtras().getDouble("log");

            showLoading();
            CreateGoogleClient();

        } else {

            lat = getIntent().getExtras().getDouble("lat");
            log = getIntent().getExtras().getDouble("log");
            // Add a marker in Sydney and move the camera
            LatLng location = new LatLng(lat, log);
            mMap.addMarker(new MarkerOptions().position(location));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
        }


    }

    @Override
    public void Show_Direction_Onmap(List<LatLng> list) {

        AppLogger.i(list.toString());
        mMap.addPolyline(new PolylineOptions()
                .addAll(list)
                .width(5)
                .color(Color.RED)
                .geodesic(true));

    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    public boolean getPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION},
                AppConstants.LOCATION_PERMISSION_REQUEST_TAG);
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {


        if (getPermission()) {


            @SuppressLint("MissingPermission")
            Location MyLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);

            if (MyLocation != null) {
                show_direction(MyLocation);
                hideLoading();

            } else {


                if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                    Log.i("getPermission    null2", "getPermission  null2");
                    createLocationRequest();
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                }

                onError("لطفا gps را روشن کنید.");
            }

        } else {
            requestPermission();
        }

    }


    void CreateGoogleClient() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(MapsActivity.this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(MapsActivity.this)
                .addOnConnectionFailedListener(MapsActivity.this)
                .build();

        mGoogleApiClient.connect();

    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }


    }

    @Override
    protected void onStop() {

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
            mGoogleApiClient.disconnect();
        }
        super.onStop();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {

                connectionResult.startResolutionForResult(this, AppConstants.CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            onError("خطا در ارتباط با گوگل");
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onLocationChanged(Location location) {


        if (location != null) {
            stopLocationUpdates();
            show_direction(location);
            hideLoading();
        } else {
            stopLocationUpdates();
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                createLocationRequest();
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == AppConstants.LOCATION_PERMISSION_REQUEST_TAG) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                hideLoading();
                onError("برای تشخیص مکان نیاز به اجازه شما داریم");
            }
        }
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(AppConstants.UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(AppConstants.FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(AppConstants.DISPLACEMENT);
    }


    public void show_direction(Location source) {

        LatLng location_source = new LatLng(source.getLatitude(), source.getLongitude());
        mMap.addMarker(new MarkerOptions().position(location_source));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location_source, 18));

        LatLng location_destination = new LatLng(lat, log);
        mMap.addMarker(new MarkerOptions().position(location_destination));

        Input_Google_Direction input_item = new Input_Google_Direction(location_source.latitude, location_source.longitude, lat, log, "driving", "fa", "metric", getResources().getString(R.string.google_maps_key), "false");
        mPresenter.Google_Direction(input_item);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GooglePlayServices.REQUEST_CODE_RECOVER_PLAY_SERVICES:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "لطفا google play service را نصب کنید",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "یکبار دیگر وارد این صفحه شوید تا نقشه بارگذاری شود",
                            Toast.LENGTH_LONG).show();

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
