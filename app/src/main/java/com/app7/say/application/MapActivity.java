package com.app7.say.application;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, LocationListener {

    private static final String TAG = "MapActivity";

    GoogleMap mMap;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123;
    boolean mLocationPermissionGranted;
    Double latitude, longitude;
    int PROXIMITY_RADIUS = 10000;
    Button button;
    private LocationManager mLocationManager;
    private Location lastlocation;



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocationPermissionGranted) {
//            //bulidGoogleApiClient();
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//               return;
//            }
            Log.d(TAG, "onMapReady: ");
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

        }
//        LatLng sydney = new LatLng(34,-151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
//        mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }


    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;

        Log.d("lat = ", "" + latitude);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitude);
        googlePlaceUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + "AIzaSyDwe3jJXcrcLK1Wm7pPYb3Ad6NeAgl60A0");

        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_people);
        mapFragment.getMapAsync(this);
        getLocationPermission();
        setupLocationManager();
        button = findViewById(R.id.B_MASYID);
        button.setOnClickListener(this);
    }

    private void setupLocationManager() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationPermissionGranted) {
            Log.d(TAG, "setupLocationManager: ");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, this);
        }
    }

    public void onClick(View v) {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        switch (v.getId()) {

            case R.id.B_MASYID:
                mMap.clear();
                String mosque = "mosque";
                String url = getUrl(latitude, longitude, mosque);
                Log.d(TAG, "onClick: lat = " + latitude + " long "+ longitude);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapActivity.this, "Showing Nearby Mosque", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }

    }


}
