package com.vit.mmsb.eureka;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Question extends AppCompatActivity {

    private static final String Q_KEY = "title";
    private static final String DESC_KEY = "description";
    private static final String CAT_KEY = "category";
    private static final String TAG = "Question";
    String cat;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        db = FirebaseFirestore.getInstance();

        Button b1 = findViewById(R.id.cancelq);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(Question.this,
                        Feed.class);
                startActivity(myIntent);
            }
        });

        Button b2 = findViewById(R.id.submitq);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){


                EditText simpleEditText = findViewById(R.id.question);
                String title = simpleEditText.getText().toString();

                simpleEditText = findViewById(R.id.desc);
                String description = simpleEditText.getText().toString();

                String[] cats = new String[] {"Chemistry", "CSE", "ECE", "EEE", "English", "Fashion", "Foreign Languages", "General", "Law", "Math", "Mechanical", "Physics", "Software"};

                Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner_category);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, cats);
                if(dynamicSpinner != null) {
                    dynamicSpinner.setAdapter(adapter);
                    dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView <?> parent, View view,
                                                   int position, long id) {
                            cat = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView <?> parent) {
                        }
                    });

                }


                Map<String, Object> Quoi = new HashMap<>();
                Quoi.put(Q_KEY, title);
                Quoi.put(DESC_KEY, description);
                Quoi.put(CAT_KEY, cat);
                db.collection("Questions").document()
                        .set(Quoi)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

                Intent myIntent = new Intent(Question.this,
                        Feed.class);
                startActivity(myIntent);
            }
        });

    }
  }