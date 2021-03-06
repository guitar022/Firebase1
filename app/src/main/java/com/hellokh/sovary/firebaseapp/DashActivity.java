package com.hellokh.sovary.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashActivity extends AppCompatActivity {

    private Button button;
    private String username, email;
    private String expenses, revenue;
    private TextView textViewUsername;
    private FirebaseAuth authDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        getSupportActionBar().setTitle("Dashboard");

        textViewUsername = findViewById(R.id.textView_show_user);

        authDash = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authDash.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(DashActivity.this, "Something went wrong! User's details are not available at the moment",
                    Toast.LENGTH_LONG).show();
        } else  {
            showUserProfile(firebaseUser);

        }
    }

    private void dashUser(String textViewExpenses, TextView textViewRevenue) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(textViewExpenses, textViewExpenses).addOnCompleteListener(DashActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                }
            }
        });
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("App Financial").child("User");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null){
                    username = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();

                    username = readUserDetails.username;
                    email = readUserDetails.email;

                    textViewUsername.setText(username);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DashActivity.this, "Something went wrong! ", Toast.LENGTH_LONG).show();
            }
        });


        Button page2 = findViewById(R.id.page2);
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this , Page2.class);
                startActivity(intent);
            }
        });

        Button page3 = findViewById(R.id.page3);
        page3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this , Page3.class);
                startActivity(intent);
            }
        });

        Button page4 = findViewById(R.id.page4);
        page4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this , Page4.class);
                startActivity(intent);
            }
        });

        Button page1 = findViewById(R.id.page1);
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this , DashActivity.class);
                startActivity(intent);
            }
        });

        Button login = findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this ,MainActivity.class);
                startActivity(intent);
            }
        });

        Button RevenueAcivity = findViewById(R.id.revenue_btn);
        RevenueAcivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this ,RevenueActivity.class);
                startActivity(intent);
            }
        });

        Button ExpensesAcivity = findViewById(R.id.expenses_btn);
        ExpensesAcivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashActivity.this ,ExpensesActivity.class);
                startActivity(intent);
            }
        });
    }
}