package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
//new imports underneath

public class buttonpage extends AppCompatActivity {
    ImageButton beerButton;
    ImageButton shotButton;
    ImageButton wineButton;
    double num_drinks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBeer();
        setContentView(R.layout.activity_buttonpage);

    }
    private void addBeer(){//adds one standard drink after beer button is pressed
        beerButton = findViewById(R.id.beerButton);
        //assigning beerButton an actual value
        beerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_drinks++; //replace with inserting data in data base with time stamp
            }
        });

    }
    private void addShot() {
        shotButton = findViewById(R.id.shotButton);
        shotButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                num_drinks++; //replace with inserting data in data base with time stamp
            }
        }));
    }

    private void addWine() {
        wineButton = findViewById(R.id.wineButton);
        wineButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_drinks++; //replace with inserting data in data base with time stamp
            }
        }));
    }
}
