package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.malaria.models.User;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegsiter;
    EditText txtName,txtEmail,txtPassword,txtConfirm;
    TextView tvLogin;
    ProgressBar progressBar;
    String name,email,password,confirm;
    FirebaseAuth mAuth;
    String userId;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user=FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null){
//            startActivity(new Intent(this,MainActivity.class));
//        }
        txtName=findViewById(R.id.etName);
        txtEmail=findViewById(R.id.etEmail);
        txtPassword=findViewById(R.id.etPassword);
        txtConfirm=findViewById(R.id.etConfirm);
        btnRegsiter=findViewById(R.id.btnRegister);
        progressBar=findViewById(R.id.progressBar);
        tvLogin=findViewById(R.id.tvLogin);

        //firebase
        mAuth= FirebaseAuth.getInstance();
        btnRegsiter.setOnClickListener(this);
        mReference=FirebaseDatabase.getInstance().getReference().child("Users");

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view==btnRegsiter){
            registerUser();
        }
    }

    private void registerUser() {

        name=txtName.getText().toString().trim();
        email=txtEmail.getText().toString().trim();
        password=txtPassword.getText().toString().trim();
        confirm=txtConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Name cannot be blank!!", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password cannot be blank!!", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(confirm)){
            Toast.makeText(this, "Please confirm your password!!", Toast.LENGTH_SHORT).show();
            return;
        }else if (!password.equals(confirm)){
            Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            return;
        }else if (password.length()<6){
            Toast.makeText(this, "Passwords length should not be less than 6!!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()){
                      sendEmailVerification();
                      progressBar.setVisibility(View.GONE);
                      OnAuth(task.getResult().getUser());
                      mAuth.signOut();
                  }
                  else{
                      if (task.getException() instanceof FirebaseAuthUserCollisionException){
                          Toast.makeText(RegisterActivity.this, "This account already exists!!", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                      }
                      else{
                          Toast.makeText(RegisterActivity.this, "Something went wrong!!Please try again.", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                      }
                  }
                }
            });
        }

    }

    private void OnAuth(FirebaseUser user) {
        createNewUser(user.getUid());
    }

    private void createNewUser(String uid) {
        User user=BuildNewUser();
    }

    private User BuildNewUser() {
        return new User(
                getDisplayName(),
                getUserEmail(),
                new Date().getTime()
        );
    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser !=null){

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
//                    Toast.makeText(RegisterActivity.this, "Method called!!", Toast.LENGTH_SHORT).show();
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Verification link has been sent to your email", Toast.LENGTH_SHORT).show();
                        txtName.getText().clear();
                        txtEmail.getText().clear();
                        txtConfirm.getText().clear();
                        txtPassword.getText().clear();
                        return;
//                        FirebaseAuth.getInstance().signOut();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Error"+task.getException(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
        else {
            Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show();
        }
    }

    public String getUserEmail() {
        return email;
    }
    public String getDisplayName() {
        return name;
    }
}