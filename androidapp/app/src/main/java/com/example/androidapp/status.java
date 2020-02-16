package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;


public class status extends AppCompatActivity {

        private TextView bacDisplay;
        private ImageView face;
        double bac = Math.random() * 4; //replace with database info here

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_status);
            displayBac(); //display the BAC level
            displayFace(); //displays face corresponding to user bac content
        }

        private void displayBac() {
            bacDisplay = findViewById(R.id.bacDisplay);
            String baclevel = Double.toString(bac);
            bacDisplay.setText(baclevel);
        }

        private void displayFace() {
            face = findViewById(R.id.face);
            if (bac >= 0 && bac < 1) {
                face.setImageResource(R.drawable.beercan);
            } else if (bac >= 1 && bac < 2) {
                face.setImageResource(R.drawable.ic_launcher_foreground);
            } else {
                face.setImageResource(R.drawable.wineglass);
            }

        }

    }

