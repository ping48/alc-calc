package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//new imports underneath

public class buttonpage extends AppCompatActivity {
    private ImageButton beerButton;
    private ImageButton shotButton;
    private ImageButton wineButton;
    private Button otherButton;
    private Button statusButton;
    private static Timestamp ts;
    private static boolean firstDrink = true;
    static String userID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userID = getIntent().getStringExtra("currentUserRefID");
        setContentView(R.layout.activity_buttonpage);
        addBeer();
        addShot();
        addWine();
        addCustom();
        gotoStatus();
    }
    public static void send_Data_firebase()
    {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(userID);
        userRef.update("totalAlcSoFar", FieldValue.increment(1));

        if(firstDrink){
            Date date = new Date();
            ts = new Timestamp(date.getTime());
            firstDrink = false;
        }

        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("alc_amount", 1);
        data.put("timestamp", FieldValue.serverTimestamp());

        if(firstDrink){
            userRef.update("firstTimestamp", FieldValue.serverTimestamp());
            firstDrink = false;
        }

        //create a subcollection drinks_record
        CollectionReference drinks_record = userRef.collection("drinks_record");

        drinks_record.add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
    private void addBeer(){//adds one standard drink after beer button is pressed
        beerButton = findViewById(R.id.beerButton);
        //assigning beerButton an actual value
        beerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_Data_firebase(); //replace with inserting data in data base with time stamp
            }
        });

    }
    private void addShot() {
        shotButton = findViewById(R.id.shotButton);
        shotButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                send_Data_firebase(); //replace with inserting data in data base with time stamp
            }
        }));
    }

    private void addWine() {
        wineButton = findViewById(R.id.wineButton);
        wineButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_Data_firebase(); //replace with inserting data in data base with time stamp
            }
        }));
    }
    private void gotoStatus() {
        statusButton = findViewById(R.id.statusButton);
        statusButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference userRef = db.collection("users").document(userID);
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        long totalAlc = dataSnapshot.child("totalAlcSoFar").getValue();
                        long start = (long)dataSnapshot.child("firstTimestamp").getValue();
                        long now = System.currentTimeMillis();
                        long elapsedTime = now - start; //in milliseconds
                        long elapsedTimeinHours = (elapsedTime * 1000) / 3600;
                        if (totalAlc == 0)
                        {
                            //send bac to be 0
                        }
                        long bac = //plug in the formula
                    }
                });
                Intent i = new Intent(buttonpage.this, status.class);
                //pass the bac # using intent
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

