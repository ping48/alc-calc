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
    double num_drinks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttonpage);
        addBeer();
        addShot();
        addWine();
        gotoStatus();
    }
    public static void send_Data_firebase()
    {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userRefID = getIntent().getStringExtra("currentUserRefID");
        DocumentReference userRef = db.collection("users").document("userRefID");
        userRef.update("totalAlcSoFar", FieldValue.increment(1));

        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("alc_amount", 1);
        data.put("timestamp", FieldValue.serverTimestamp());

        //create a subcollection drinks_record
        CollectionReference drinks_record = userRef.collection("drinks_record");

        drinks_record.add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
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
                Intent i = new Intent(buttonpage.this, status.class);
                startActivity(i);
            }
        }));

    }
}
