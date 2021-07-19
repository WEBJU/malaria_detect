package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malaria.models.Feedback;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSend,btnReport;
    EditText edtFeedback;
    String userId;
    String feedback;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        edtFeedback=findViewById(R.id.etFeedback);
        btnSend=findViewById(R.id.btnSend);
        btnReport=findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedbackActivity.this,StatisticActivity.class));
            }
        });
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSend){
            sendFeedback();
        }
    }

    private void sendFeedback() {
        feedback=edtFeedback.getText().toString().trim();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();

        }
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Feedback");
        Feedback user=new Feedback(userId,feedback);
        databaseReference.child(userId).setValue(user);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(FeedbackActivity.this, "Your feedback has been received!!Thank you for choosing us.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FeedbackActivity.this,MainActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedbackActivity.this, "Something went wrong!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}