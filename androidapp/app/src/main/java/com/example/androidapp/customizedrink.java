package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class customizedrink extends buttonpage {

    //declare edittexts
    private EditText ouncesDrankTEXT;
    private EditText percentAlcTEXT;
    //toString the editTexts
    private String percentAlcString;
    private String ouncesDrankString;
    //set the doubles to the text
    private double percentAlc;
    private double ouncesDrank;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customizedrink);
        //
        defalcdouble();
        defozdouble();
        addDrink();

    }
    private void addDrink(){
        add =  findViewById(R.id.addDrink);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(customizedrink.this, buttonpage.class);
                TextView beep1 = (TextView) findViewById(R.id.beep);
                defalcdouble();
                defozdouble();
                if(percentAlc == 0.0 || ouncesDrank == 0.0)
                    beep1.setText("Please complete all fields");
                else{
                    beep1.setText("");
                    startActivity(i);
                }
            }
        });
    }
    private void defalcdouble() {
        percentAlc = 0.0;
        percentAlcTEXT = findViewById(R.id.percentAlc);
        percentAlcString = percentAlcTEXT.getText().toString();
        if (!percentAlcString.equals(""))
            percentAlc = Double.parseDouble(percentAlcString);
    }
    private void defozdouble(){
        ouncesDrank = 1.0;
        ouncesDrankTEXT = findViewById(R.id.ouncesDrank);
        ouncesDrankString = ouncesDrankTEXT.getText().toString();
        if(!ouncesDrankString.equals(""))
            ouncesDrank = Double.parseDouble(ouncesDrankString);
    }
}

