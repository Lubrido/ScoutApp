package com.example.tudelftsid.scout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CompleteScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_screen);
    }

    public void startMainActivity(View view) {
        Intent myIntent = new Intent(this,HomeScreen.class);
        startActivity(myIntent);
    }
}
