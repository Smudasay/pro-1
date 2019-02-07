package com.app7.say.application;


import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class CompassActivity extends AppCompatActivity implements SensorEventListener, OnMapReadyCallback, View.OnClickListener,LocationListener {

    ImageView compass_img;
    TextView txt_compass;
    int mAzimuth;
    private SensorManager mSensorManager;
    private Sensor mRotationV, mAccelerometer, mMagnetometer;
    boolean haveSensor = false, haveSensor2 = false;
    float[] rMat = new float[9];
    float[] orientation = new float[3];
    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        compass_img = (ImageView) findViewById(R.id.img_compass);
        txt_compass = (TextView) findViewById(R.id.txt_azimuth);

        start();

        //map

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_people);
        mapFragment.getMapAsync(this);
        getLocationPermission();
        setupLocationManager();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rMat, event.values);
            mAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rMat, orientation)[0]) + 360) % 360;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;
        }
        if (mLastAccelerometerSet && mLastMagnetometerSet) {
            SensorManager.getRotationMatrix(rMat, null, mLastAccelerometer, mLastMagnetometer);
            SensorManager.getOrientation(rMat, orientation);
            mAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rMat, orientation)[0]) + 360) % 360;
        }

        mAzimuth = Math.round(mAzimuth);
        compass_img.setRotation(-mAzimuth);

        String where = "NW";

        if (mAzimuth >= 350 || mAzimuth <= 10)
            where = "N";
        if (mAzimuth < 350 && mAzimuth > 280)
            where = "NW";
        if (mAzimuth <= 280 && mAzimuth > 260)
            where = "W";
        if (mAzimuth <= 260 && mAzimuth > 190)
            where = "SW";
        if (mAzimuth <= 190 && mAzimuth > 170)
            where = "S";
        if (mAzimuth <= 170 && mAzimuth > 100)
            where = "SE";
        if (mAzimuth <= 100 && mAzimuth > 80)
            where = "E";
        if (mAzimuth <= 80 && mAzimuth > 10)
            where = "NE";


        txt_compass.setText(mAzimuth + "° " + where);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void start() {
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) == null) {
            if ((mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) || (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null)) {
                noSensorsAlert();
            } else {
                mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                haveSensor = mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
                haveSensor2 = mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_UI);
            }
        } else {
            mRotationV = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            haveSensor = mSensorManager.registerListener(this, mRotationV, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void noSensorsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your device doesn't support the Compass.")
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        alertDialog.show();
    }

    public void stop() {
        if (haveSensor) {
            mSensorManager.unregisterListener(this, mRotationV);
        } else {
            mSensorManager.unregisterListener(this, mAccelerometer);
            mSensorManager.unregisterListener(this, mMagnetometer);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }
//}

    //ตำแน่งปัจจุบัน

   // public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, LocationListener {

    private static final String TAG = "MapActivity";
    GoogleMap mMap;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123;
    boolean mLocationPermissionGranted;
    Double latitude, longitude;
    int PROXIMITY_RADIUS = 10000;
    Button button;
    private LocationManager mLocationManager;
    private Location lastlocation;


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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_compass);
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        getLocationPermission();
//        setupLocationManager();
//    }

    private void setupLocationManager() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationPermissionGranted) {
            Log.d(TAG, "setupLocationManager: ");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, this);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {

    }
}



//
//import android.annotation.SuppressLint;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class CompassActivity extends AppCompatActivity {
//
//
//
//
////    NotificationCompat.Builder mBuilder;
////    NotificationManager mNotifyMgr;
////    NotificationManager nMgr;
////    private Button btnremove;
////    private TextView sound2;
////
////    @SuppressLint("WrongViewCast")
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_compass);
////        sound2 = findViewById(R.id.sound2);
////        sound2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                mNotifyMgr =
////                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
////// Builds the notification and issues it.
////                mNotifyMgr.notify(1, mBuilder.build());
////                Toast.makeText(CompassActivity.this,"You have new Message",Toast.LENGTH_LONG).show();
////            }
////
////        });
////
////        //Notification Builder
////        mBuilder =
////                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
////                        .setSmallIcon(R.drawable.ic_stat_name)
////                        .setContentTitle("New notification")
////                        .setContentText("Open Google website!");
////
////        Intent resultIntent = new Intent(Intent.ACTION_VIEW,
////                Uri.parse("http://www.google.com/"));
////        //  Define notification action
////        PendingIntent resultPendingIntent =
////                PendingIntent.getActivity(
////                        this,   // context
////                        0,      // requestCode (defined PendingIntent id)
////                        resultIntent,
////                        // if a previous PendingIntent already exists,
////                        // then the current one will update it with the latest intent
////                        PendingIntent.FLAG_UPDATE_CURRENT);
////        mBuilder.setContentIntent(resultPendingIntent);
////
////    }
//
//}

