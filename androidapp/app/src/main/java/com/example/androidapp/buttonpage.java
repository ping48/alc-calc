package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
//new imports underneath

public class buttonpage extends AppCompatActivity {
    private ImageButton beerButton;
    private ImageButton shotButton;
    private ImageButton wineButton;
    private Button otherButton;
    private Button statusButton;
    public double num_drinks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttonpage);
        addBeer();
        addShot();
        addWine();
        gotoStatus();
        addCustom();
        configureCalculator();
    }
    private void configureCalculator(){
        Button calc = (Button) findViewById(R.id.manual);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = getIntent().getStringExtra("weight");
                String gendVal = getIntent().getStringExtra("genderValue");
                Intent k = new Intent(buttonpage.this, Calculator.class);
                k.putExtra("weight",weight);
                k.putExtra("genderValue",gendVal);
                startActivity(k);
            }
        });
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
    private void gotoStatus() {
        statusButton = findViewById(R.id.statusButton);
        statusButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(buttonpage.this, status.class);
                startActivity(i);
            }
        }));
        }
    private void addCustom() {
        otherButton = findViewById(R.id.otherButton);
        otherButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(buttonpage.this, customizedrink.class);
                startActivity(j);
            }
        }));

    }

    }

