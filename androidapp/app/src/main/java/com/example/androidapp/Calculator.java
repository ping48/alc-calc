package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        configuretheButton();
        configureBackButton();
    }
    private void configuretheButton(){
        Button calcButton = (Button) findViewById(R.id.calcu);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nums = findViewById(R.id.num);
                String nums1 = nums.getText().toString();
                double nums2 = 0.0;
                if(!(nums1.equals("")))
                    nums2 = Integer.parseInt(nums1);
                EditText time = findViewById(R.id.timef);
                String time1 = time.getText().toString();
                double time2 = 0.0;
                if(!(time1.equals("")))
                    time2 = Integer.parseInt(time1);
                String weight = getIntent().getStringExtra("weight");
                double weights = Double.parseDouble(weight);
                String gendVal = getIntent().getStringExtra("genderValue");
                double gends = Double.parseDouble(gendVal);
                double theBAC = ((nums2 * 3.75) * 5.14 / (weights * gends)) - (0.015 * time2);
                TextView bacLevel = findViewById(R.id.baclevel);
                if(time2 == 0 || nums2 == 0)
                    bacLevel.setText("Please complete all fields");
                else
                    bacLevel.setText("Your BAC is: " + theBAC);
            }
        });
    }
    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Calculator.this, buttonpage.class);
                startActivity(i);
            }
        });
    }
}
