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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RadioGroup gender;
    private RadioButton theButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gender = (RadioGroup) findViewById(R.id.genders);
        configureNextButton();
        test();
    }
    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.submit);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText feet = findViewById(R.id.heightFoot);
                String feet1 = feet.getText().toString();
                int feet2 = Integer.parseInt(feet1);
                EditText inch = findViewById(R.id.heightInches);
                String inch1 = inch.getText().toString();
                double inch2 = Integer.parseInt(inch1);
                double totalInches = (12.0 * (feet2 + 0.0) + inch2);
                EditText pounds = findViewById(R.id.weight);
                String pounds1 = pounds.getText().toString();
                //
                int radioId = gender.getCheckedRadioButtonId();
                theButton = findViewById(radioId);
                double gendVal;
                if(((String)(theButton.getText())).equals("Male"))
                    gendVal = 0.73;
                else
                    gendVal = 0.66;
                //
                int pounds2 = Integer.parseInt(pounds1);
                //change this to ButtonPage
                Intent i = new Intent(MainActivity.this, buttonpage.class);
                i.putExtra("height", totalInches);
                i.putExtra("weight", pounds2);
                i.putExtra("genderValue", gendVal);
                startActivity(i);
            }
        });
    }
    public void checkButton(View v){
        int radioId = gender.getCheckedRadioButtonId();
        theButton = findViewById(radioId);
        Toast.makeText(this, theButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public static void test(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference myRef = database.getReference("message");
////
////        myRef.setValue("Hello, World!");
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        String userID = "XK7AARkwcgBuQNm8LtJX78797";
//        User user = new User();
//        user.setU_height(5);
//        user.setU_weight(10);
//
//        DatabaseReference firebaseUser = mDatabase.child("users").child(userID);
//        firebaseUser.setValue(user);
//
//        System.out.println("test");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

//    public static void main(String [] args){
//        System.out.println("wadaf");
//        test();
//    }
}
