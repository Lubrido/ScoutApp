package com.example.tudelftsid.scout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity {

    private Button useHintButton;
    private ProgressBar progress;
    private TextView temperature;
    private TextView pointsLeft;

    private int backPress;
    private double destinationLatitude;
    private double destinationLongitude;
    private double distanceMeters;
    private double temp;
    public static int point;
    public static int clickCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        useHintButton = (Button) findViewById(R.id.b1);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        temperature = (TextView) findViewById(R.id.Temp);
        pointsLeft = (TextView) findViewById(R.id.Points);

        setDestination();

        hintButton();
    }

    void setDestination(){
        destinationLongitude = 4.350708 ;
        destinationLatitude = 52.006795;
    }

    void calculateMeters(){
        double R = 6378.137; // Radius of earth in KM
        double dLat = destinationLatitude * Math.PI / 180 - HomeScreen.gpsLatitude * Math.PI / 180;
        double dLon = destinationLongitude * Math.PI / 180 - HomeScreen.gpsLongitude * Math.PI / 180;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(HomeScreen.gpsLatitude * Math.PI / 180) * Math.cos(destinationLatitude * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        distanceMeters = d * 1000;
        Log.i("distanceMeters","the distance is" + distanceMeters);
    }

    void hintButton(){
        useHintButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                calculateMeters();
                temperature.setVisibility(View.VISIBLE);
                if(distanceMeters >2000){
                    temperature.setText("You froze");
                }
                else {
                    useHintButton.setText("Use hint\n-10 points");
                    clickCount=clickCount+1;
                    Log.i("clickcount","click is" + clickCount);
                    int b = clickCount-1;
                    point = 200-b*10;
                    pointsLeft.setText(point +"\npoints");
                    if(point<=1){
                        pointsLeft.setText("No points left");
                        useHintButton.setText("Use hint");
                    }
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
    public void onBackPressed() {
        backPress = backPress + 1;
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backPress>1) {
            Intent i2=new Intent(GameScreen.this, MainScreen.class);
            startActivity(i2);
        }
    }
}


