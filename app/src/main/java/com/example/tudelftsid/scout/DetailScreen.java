package com.example.tudelftsid.scout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailScreen extends AppCompatActivity {

    TextView itemText;
    ImageView photo;
    Button find;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        itemText = findViewById(R.id.description);
        photo = findViewById(R.id.mainphoto);
        find = findViewById(R.id.findbutton);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            itemText.setText(bundle.getString("description"));
            if (itemText.getText().toString().equalsIgnoreCase("Delft")) {
                photo.setImageDrawable(ContextCompat.getDrawable(DetailScreen.this, R.drawable.delft));
            } else if (itemText.getText().toString().equalsIgnoreCase("Nature")) {
                photo.setImageDrawable(ContextCompat.getDrawable(DetailScreen.this, R.drawable.nature_1));
            }

        }
    }

    public void locateActivity(View view) {
        Intent myIntent = new Intent(this,GameScreen.class);
        startActivity(myIntent);
    }
}
