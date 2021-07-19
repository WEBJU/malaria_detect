package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.malaria.models.Treatment;

public class InformationActivity extends AppCompatActivity {
    EditText etTitle,etDescription;
    Button add;
    String title,description,userId;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference treatmentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        etTitle=findViewById(R.id.edtTitle);
        etDescription=findViewById(R.id.edtDescription);
        add=findViewById(R.id.btnAdd);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        treatmentRef=database.getReference("Treatment");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTreatment();
            }
        });
    }

    private void addTreatment() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            userId=user.getUid();
        }
        title=etTitle.getText().toString().trim();
        description=etDescription.getText().toString();

        if (TextUtils.isEmpty(title)){
            Toast.makeText(this, "Please fill in medication title..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(description)){
            Toast.makeText(this, "Please fill in medication description..", Toast.LENGTH_SHORT).show();
            return;
        }
        Treatment treatment=new Treatment(title,description,userId);
        treatmentRef.push().setValue(treatment);
        treatmentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(InformationActivity.this, "New treatment method added successfully!!", Toast.LENGTH_SHORT).show();
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}