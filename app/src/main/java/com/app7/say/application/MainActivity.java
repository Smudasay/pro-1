package com.app7.say.application;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.app7.say.application.Utils.TimeUtils;
import com.app7.say.application.model.Datum;
import com.app7.say.application.model.PrayerTime;
import com.app7.say.application.model.PrayerTimes;
import com.app7.say.application.model.Timings;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123;
    private static final String TAG = "MainActivity";
    boolean mLocationPermissionGranted = false;
    Double latitude, longitude;
    int PROXIMITY_RADIUS = 10000;
    private LocationManager mLocationManager;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] mDrawerTitle = {"HOME", "PRAYER TIME", "FIND A MOSQUE", "FIND A LOCATION", "CONTACT US"};
    private ListView mListView;
    private TextView tv1;
    private ImageView quran;
    private ImageView dua;
    private ImageView musyid;
    private ImageView solat;
    private ImageView sound;
    private ImageView map;
    private TextView tPrayerTime;
    private TextView tdate;

    private PrayerTime prayerTime = null;
    private TimeUtils timeUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeUtils = new TimeUtils();
        getLocationPermission();
        setupLocationManager();
        tdate=findViewById(R.id.date_time1);

        tPrayerTime = (TextView) findViewById(R.id.date_time);
        // final TextView tv1 = findViewById(R.id.tv1);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout,
                R.string.open_drawer,
                R.string.close_drawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.drawer);
        quran = findViewById(R.id.quran);
        quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QuranActivity.class);
                startActivity(i);
            }

        });
        mListView = (ListView) findViewById(R.id.drawer);
        dua = findViewById(R.id.dua);
        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DuaActivity.class);
                startActivity(i);

            }
        });
        mListView = (ListView) findViewById(R.id.drawer);
        musyid = findViewById(R.id.musyid);
        musyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MusyidActivity.class);
                startActivity(i);

            }
        });

        mListView = (ListView) findViewById(R.id.drawer);
        solat = findViewById(R.id.solat);
        solat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SolatActivity.class);
                startActivity(i);

            }
        });
        mListView = (ListView) findViewById(R.id.drawer);
        sound = findViewById(R.id.sound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CompassActivity.class);
                startActivity(i);

            }
        });
        mListView = (ListView) findViewById(R.id.drawer);
        map = findViewById(R.id.map_people);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapActivity.class);
                startActivity(i);

            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mDrawerTitle);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) mListView.getItemAtPosition(position);
                mDrawerLayout.closeDrawers();
                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, MusyidActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, MainActivity4.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, MainActivity5.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;
                }
                //   tv1.setText("Position :"+ position+"  ListItem : " +itemValue);
                Toast.makeText(getApplicationContext(),
                        "Position :" + position + "  ListItem : " + itemValue, Toast.LENGTH_SHORT)
                        .show();

            }
        });

        //Custom ListView
//        int[] resId = { R.drawable.aerithgainsborough
//                , R.drawable.barretwallace, R.drawable.caitsith
//                , R.drawable.cidhighwind, R.drawable.cloudstrife
//        };

//        String[] list = { "Aerith Gainsborough", "Barret Wallace", "Cait Sith"
//                , "Cid Highwind", "Cloud Strife", "RedXIII", "Sephiroth"
//                , "Tifa Lockhart", "Vincent Valentine", "Yuffie Kisaragi"
//                , "ZackFair" };
//        CustomAdapter customadapter = new CustomAdapter(getApplicationContext(), list, resId);
//        ListView listView = (ListView)findViewById(R.id.listView1);
//        listView.setAdapter(customadapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long row_id) {
//                TextView textView = (TextView)view.findViewById(R.id.textView1);
//                System.out.println( textView.getText());
//                Toast.makeText(getApplicationContext(), "Clicked on item:" +  position , Toast.LENGTH_SHORT).show();
//            }
//      });
        //date&time
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(prayerTime!=null){
                                    Timings timings = prayerTime.getData().get(timeUtils.getDay()-1).getTimings();
                                    if(timeUtils.getHour()>=1 && timeUtils.getHour()<=6){
                                        tPrayerTime.setText(extractTime(timings.getFajr()) + " (Fajr)");
                                    }else if(timeUtils.getHour()>=6 && timeUtils.getHour()<=15){
                                        tPrayerTime.setText(extractTime(timings.getDhuhr()) + " (Duhr)");
                                    }else if(timeUtils.getHour()>=15 && timeUtils.getHour()<=18){
                                        tPrayerTime.setText(extractTime(timings.getAsr()) + " (Asr)");
                                    }else if(timeUtils.getHour()>=18 && timeUtils.getHour()<=19){
                                        tPrayerTime.setText(extractTime(timings.getMaghrib()) + " (Maqrib)");
                                    }else{
                                        tPrayerTime.setText(extractTime(timings.getIsha()) + " (Isha)");
                                    }
                                }
//
                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };
        t.start();


    }

    private String extractTime(String prayerTime){
        return prayerTime.split(" ")[0];
    }

    private String getApiUrl(int month,int year,LatLng latLng){
        double lat = latLng.latitude;
        double lng = latLng.longitude;
        String str = "http://api.aladhan.com/v1/calendar?latitude="+lat+"&longitude=-"+lng+"&method=2&month="+month+"&year="+year;
        Log.d(TAG, "getApiUrl: " + str);
        return str;
    }

    class GetApiTask extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            try{
            Request request = new Request.Builder()
                        .url(strings[0])
                        .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
            }catch(IOException e){
                Log.e("error", "doInBackground: ", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            if(response!=null){
                Log.d("test", "onPostExecute: " + response);

                Gson gson = new Gson();
                prayerTime = gson.fromJson(response,PrayerTime.class);

                String str = prayerTime.getData().get(timeUtils.getDay()-1).getDate().getHijri().getDay();
                String str1 = prayerTime.getData().get(timeUtils.getDay()-1).getDate().getHijri().getMonth().getAr();
                String str2 = prayerTime.getData().get(timeUtils.getDay()-1).getDate().getHijri().getYear();
                tdate.setText(str+" "+str1+" "+str2);


            }
        }
    }

    private void setupLocationManager() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationPermissionGranted) {
            Log.d(TAG, "setupLocationManager: ");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if(prayerTime==null)
                        new GetApiTask().execute(getApiUrl(timeUtils.getMonth(),timeUtils.getYear(),new LatLng(location.getLatitude(),location.getLongitude())));
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
            });
        }else{
            getLocationPermission();
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





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //when click Hamburger will show the menu
//        if (actionBarDrawerToggle.onOptionsItemSelected(item))
//            return true;
//
//        switch (item.getItemId()) {
//            case R.id.mnuNew:
//                Toast.makeText(this, "New!", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.mnuHelp:
//                Toast.makeText(this, "Help!", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//
//    }

    //Hamburger
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


}
