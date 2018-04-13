package com.example.tudelftsid.scout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void startMainActivity(View view) {
        Intent myIntent = new Intent(this,GameScreen.class);
        startActivity(myIntent);
    }
}