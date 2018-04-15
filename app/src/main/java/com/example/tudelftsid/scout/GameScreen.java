package com.example.tudelftsid.scout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private Button useHintButton;
    private ProgressBar progress;
    private TextView temperature;
    private LocationManager locationManager;
    private double destinationLatitude;
    private double destinationLongitude;
    private double gpsLongitude;
    private double gpsLatitude;
    private double distanceMeters;
    private double temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        useHintButton = (Button) findViewById(R.id.b1);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        temperature = (TextView) findViewById(R.id.Temp);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            },1 );
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListenerGPS);

        setDestination();

        hintButton();
    }

   LocationListener locationListenerGPS= new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                gpsLongitude = location.getLongitude();
                gpsLatitude = location.getLatitude();
                Log.i("locationlat","the lat is" + gpsLatitude);
                Log.i("locationlong","the long is" + gpsLongitude);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

    void setDestination(){
        destinationLongitude = 4.350866 ;
        destinationLatitude = 52.007103;
    }

    void calculateMeters(){
        double R = 6378.137; // Radius of earth in KM
        double dLat = destinationLatitude * Math.PI / 180 - gpsLatitude * Math.PI / 180;
        double dLon = destinationLongitude * Math.PI / 180 - gpsLongitude * Math.PI / 180;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(gpsLatitude * Math.PI / 180) * Math.cos(destinationLatitude * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        distanceMeters = d * 1000;
        Log.i("distanceMeters","the distance is" + distanceMeters);
    }

    void hintButton(){
        useHintButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                temperature.setVisibility(View.VISIBLE);
                calculateMeters();
                if(distanceMeters >2000){
                    temperature.setText("You froze");
                }
                else {
                    double a = (-1D / 40);
                    temp = a * distanceMeters + 30;
                    temperature.setText(String.format("%.1f °C",temp));
                    int progressTemp = (int)temp + 20;
                    int progressScale=0;
                    while (progressScale < progressTemp){
                        progressScale++;
                        progress.setProgress(progressScale);
                    }
                }
                if (distanceMeters < 20){
                    Intent i=new Intent(GameScreen.this, CompleteScreen.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                hintButton();
                break;
            default:
                break;
        }
    }
}


