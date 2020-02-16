package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.User;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private RadioGroup gender;
    private RadioButton theButton;
    private static double gend;
    private static double height;
    private static int weight;
    private static DocumentReference curUserRef;
    private static String curUserRefID = "";
    private static boolean firstDrink = true;
    private static Timestamp ts;
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
                height = totalInches;
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

                //change this to ButtonPage
                Intent i = new Intent(MainActivity.this, buttonpage.class);
                i.putExtra("height", totalInches);
                i.putExtra("weight", pounds2);
                i.putExtra("genderValue", gendVal);
                send_data_to_firebase();
                i.putExtra("currentUserRefID", curUserRefID);
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

    public static void send_data_to_firebase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("gender", gend);
        user.put("weight", weight);
        user.put("height", height);
        user.put("totalAlcSoFar", 0);
        user.put("firstTimestamp", "");



//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
////                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
////                        Log.w(TAG, "Error adding document", e);
//                    }
//                });




// Add a new document with a generated ID
        curUserRef = db.collection("users").document();
        curUserRefID = curUserRef.getId();
        curUserRef.set(user);
    }

}
