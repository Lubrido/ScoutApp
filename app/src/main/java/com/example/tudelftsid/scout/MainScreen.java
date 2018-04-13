package com.example.tudelftsid.scout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    public TextView itemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        final String[] places = new String[] {"Delft", "Nature", "Streetart", "Delft", "Nature", "Streetart"};
        // Replace the Array adapter with your custom adapter.
        ListAdapter theListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, places);
        final ListAdapter customListAdapter = new CustomAdapter(this,places);// Pass the food arrary to the constructor.
        ListView customListView = (ListView) findViewById(R.id.custom_ListView);
        customListView.setAdapter(customListAdapter);

       /* itemText=(TextView)findViewById(R.id.item_text);
          itemText.setVisibility(View.INVISIBLE);



       customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //itemtext.setVisibility(View.VISIBLE);
                    }
                }
        );
        */
    }
}
