package com.androidapp.bestlocprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LocationManager locManager;
    TextView txtInfo;
    Criteria criteria;
    String bestProvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txtInfo);
        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        bestProvName = locManager.getBestProvider(criteria, enabledOnly: true);
        txtInfo.setText("Best Loc. provider: " + bestProvName);
    }
}