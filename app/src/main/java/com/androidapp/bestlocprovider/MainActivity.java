package com.androidapp.bestlocprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LocationManager locManager;
    LocationListener locListener;
    TextView myLocField;
    Criteria criteria;
    String bestProvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLocField = findViewById(R.id.myLocationField);
        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        bestProvName = locManager.getBestProvider(criteria, true);
    //    myLocField.setText("Best Loc. provider: " + bestProvName);

        locListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                myLocField.setText(
                        "Latitude: " + location.getLatitude()
                        + "\nLongtitude: " + location.getLongitude()
                        + "\nAltitude: " + location.getAltitude());
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locManager.removeUpdates(locListener);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}