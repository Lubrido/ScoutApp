package com.example.tudelftsid.scout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class CompleteScreen extends AppCompatActivity {

    private TextView pointsEarned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_screen);

      pointsEarned = (TextView) findViewById(R.id.pointsEarned);

     setText();

    }

    void setText(){
        if(GameScreen.point<=1){
            pointsEarned.setText("No points earned");
        } else {
            pointsEarned.setText(GameScreen.point + "points");
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    public void startMainActivity(View view) {
        GameScreen.clickCount=0;
        GameScreen.point=200;
        Intent myIntent = new Intent(this,MainScreen.class);
        startActivity(myIntent);
    }
}
