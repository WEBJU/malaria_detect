package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.malaria.models.Users;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference profileRef;
    FirebaseDatabase users;
    FirebaseUser authUser;
    EditText edtName,edtEmail,edtExperience,edtLocation,edtAddress,edtSpecialization,edtHospital;
    Button update;
    String name,email,experience,location,address,specialization,hospital,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        edtName=findViewById(R.id.etName);
        edtEmail=findViewById(R.id.etEmail);
        edtExperience=findViewById(R.id.etExperience);
        edtLocation=findViewById(R.id.etLocation);
        edtAddress=findViewById(R.id.etAddress);
        edtSpecialization=findViewById(R.id.etSpecialization);
        edtHospital=findViewById(R.id.etHospital);

        update=findViewById(R.id.btnUpdate);
        update.bringToFront();
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == update){
            Toast.makeText(this, "Clicked!!", Toast.LENGTH_SHORT).show();
            getUserDetails();
        }
    }

    private void getUserDetails() {
        authUser= FirebaseAuth.getInstance().getCurrentUser();
        if (authUser != null){
            uid=authUser.getUid();
        }
        name=edtName.getText().toString().trim();
        email=edtEmail.getText().toString().trim();
        experience=edtExperience.getText().toString().trim();
        hospital=edtHospital.getText().toString();
        location=edtLocation.getText().toString().trim();
        address=edtAddress.getText().toString().trim();
        specialization=edtSpecialization.getText().toString().trim();
        if (name ==null){
            edtName.setError("Name cannot be empty");
        }
        updateProfile(name,email,address,experience,hospital,location,specialization,uid);
    }

    private void updateProfile(String name, String email, String address, String experience, String hospital, String location, String specialization, String uid) {
        users=FirebaseDatabase.getInstance();
        profileRef=users.getReference("Users");
        profileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users profile=new Users(name,email,address,experience,hospital,location,specialization,uid);
                profileRef.child(uid).setValue(profile);
                Toast.makeText(ProfileActivity.this, "Your profile has been updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}