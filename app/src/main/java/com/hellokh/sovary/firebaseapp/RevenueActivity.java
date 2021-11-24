package com.hellokh.sovary.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RevenueActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private Button submitbtn;
    private DatabaseReference reff;
    ReadWriteUserDetails readWriteUserDetails;
    private Button clearbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        editTextNumber = findViewById(R.id.edittext_number_1);
        submitbtn = findViewById(R.id.Submit_btn);
        reff = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        getSupportActionBar().setTitle("Revenue");
        readWriteUserDetails = new ReadWriteUserDetails();
        clearbtn = findViewById(R.id.clear_btn);

        Button buttonBack = findViewById(R.id.Back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevenueActivity.this, DashActivity.class);
                startActivity(intent);
            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long revenue1 = Long.parseLong(editTextNumber.getText().toString());

                readWriteUserDetails.setExp(revenue1);

                reff.child("revenue").setValue(readWriteUserDetails);
                Toast.makeText(RevenueActivity.this, "Record Successfully", Toast.LENGTH_LONG).show();
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.getText().clear();
            }
        });
    }
}