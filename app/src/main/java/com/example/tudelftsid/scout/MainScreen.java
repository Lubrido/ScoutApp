package com.example.tudelftsid.scout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        final String[] places = new String[]{"Delft", "Nature", "Streetart", "Delft", "Nature", "Streetart"};
        final ListAdapter customListAdapter = new CustomAdapter(this, places);// Pass the places array to the constructor.
        final ListView customListView = findViewById(R.id.custom_ListView);
        customListView.setAdapter(customListAdapter);

        customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                        Intent intent = new Intent(MainScreen.this, DetailScreen.class);
                        intent.putExtra("description", customListView.getItemAtPosition(i).toString());
                        startActivity(intent);
                    }
                }
        );
    }
}
