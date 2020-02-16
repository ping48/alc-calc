package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class customizedrink extends buttonpage {

    //declare edittexts
    private EditText ouncesDrankTEXT = findViewById(R.id.ouncesDrank);
    private EditText percentAlcTEXT = findViewById(R.id.percentAlc);
    //toString the editTexts
    private String percentAlcString = percentAlcTEXT.getText().toString();
    private String ouncesDrankString = ouncesDrankTEXT.getText().toString();
    //set the doubles to the text
    private double percentAlc = 0.0;
    private double ouncesDrank = 0.0;
    private Button add = findViewById(R.id.addDrink);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customizedrink);
        //
        addDrink();
        defalcdouble();
        defozdouble();
    }
    private void addDrink(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(customizedrink.this, buttonpage.class);
                startActivity(i);
                 num_drinks+=ouncesDrank*percentAlc;
            }
        });
    }
    private void defalcdouble() {
        if (!percentAlcString.equals(""))
            percentAlc = Double.parseDouble(percentAlcString);
    }
    private void defozdouble(){
        if(!ouncesDrankString.equals(""))
            ouncesDrank = Double.parseDouble(ouncesDrankString);
    }
}

