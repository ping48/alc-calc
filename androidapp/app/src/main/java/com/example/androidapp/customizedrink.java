package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class customizedrink extends buttonpage {

    //declare edittexts
    private EditText ouncesDrankTEXT;
    private EditText percentAlcTEXT;
    //toString the editTexts
    private String percentAlcString;
    private String ouncesDrankString;
    //set the doubles to the text
    private double percentAlc = 0.0;
    private double ouncesDrank = 0.0;
    private Button add;
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
        add =  findViewById(R.id.addDrink);
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
        percentAlcTEXT = findViewById(R.id.percentAlc);
        percentAlcString = percentAlcTEXT.getText().toString();
        if (!percentAlcString.equals(""))
            percentAlc = Double.parseDouble(percentAlcString);
    }
    private void defozdouble(){
        ouncesDrankTEXT = findViewById(R.id.ouncesDrank);
        ouncesDrankString = ouncesDrankTEXT.getText().toString();
        if(!ouncesDrankString.equals(""))
            ouncesDrank = Double.parseDouble(ouncesDrankString);
    }
}

