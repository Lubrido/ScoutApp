package com.example.tudelftsid.scout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private int ItemClicked;
    ArrayList<Place> arrayList;
    ListView customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ItemClicked=0;

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_screen);
            arrayList = new ArrayList<>();
            customListView = findViewById(R.id.custom_ListView);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://145.94.181.25:8081/loadpictures");
                }
            });

            customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                        Toast.makeText(getApplicationContext(), "Hold to Scout to Location", Toast.LENGTH_SHORT).show();
                    }
                });

            customListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainScreen.this, GameScreen.class);
                intent.putExtra("position",i);
                startActivity(intent);
                return true;
                }
            });
        }

        class ReadJSON extends AsyncTask<String, Integer, String> {

            @Override
            protected String doInBackground(String... params) {
                return readURL(params[0]);
            }

            @Override
            protected void onPostExecute(String content) {
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    JSONArray jsonArray =  jsonObject.getJSONArray("Picturesss");

                    for(int i =0;i<jsonArray.length(); i++){
                        JSONObject placeObject = jsonArray.getJSONObject(i);
                        arrayList.add(new Place(
                                placeObject.getString("image"),
                                placeObject.getString("name"),
                                placeObject.getString("LocationLong"),
                                placeObject.getString("LocationLat")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomListAdapter adapter = new CustomListAdapter(
                        getApplicationContext(), R.layout.custom_row, arrayList
                );
                customListView.setAdapter(adapter);
            }
        }

        private static String readURL(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {
                // create a url object
                URL url = new URL(theUrl);
                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();
                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
