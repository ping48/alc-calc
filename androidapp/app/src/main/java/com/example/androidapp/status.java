package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class status extends AppCompatActivity {
    private TextView bacDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        double bac = 0.0; //replace with database info here

        displayBac();
    }

    private void displayBac(){
        bacDisplay = findViewById(R.id.bacDisplay);

    }
}
