package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup gender;
    private RadioButton theButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gender = (RadioGroup) findViewById(R.id.genders);
        configureNextButton();
    }
    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.submit);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText feet = findViewById(R.id.heightFoot);
                String feet1 = feet.getText().toString();
                int feet2 = 0;
                if(!(feet1.equals("")))
                    feet2 = Integer.parseInt(feet1);
                EditText inch = findViewById(R.id.heightInches);
                String inch1 = inch.getText().toString();
                double inch2 = 0.0;
                if(!(inch1.equals("")))
                    inch2 = Integer.parseInt(inch1);
                double totalInches = (12.0 * (feet2 + 0.0) + inch2);
                EditText pounds = findViewById(R.id.weight);
                String pounds1 = pounds.getText().toString();
                int pounds2 = 0;
                if(!(pounds1.equals("")))
                    pounds2 = Integer.parseInt(pounds1);
                //
                int radioId = gender.getCheckedRadioButtonId();
                double gendVal = 0.0;
                if(radioId != -1) {
                    theButton = findViewById(radioId);
                    if ((theButton.getText().toString()).equals("Male"))
                        gendVal = 0.73;
                    else
                        gendVal = 0.66;
                }
                //
                //change this to ButtonPage
                Intent i = new Intent(MainActivity.this, buttonpage.class);
                i.putExtra("height", totalInches);
                i.putExtra("weight", pounds2);
                i.putExtra("genderValue", gendVal);
                TextView warn = findViewById(R.id.warning);
                if(radioId == -1 || totalInches == 0 || pounds2 == 0){
                    warn.setText("Please complete all fields");
                }
                else{
                    warn.setText("");
                    startActivity(i);
                }

            }
        });
    }
    public void checkButton(View v){
        int radioId = gender.getCheckedRadioButtonId();
        theButton = findViewById(radioId);
        Toast.makeText(this, theButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
