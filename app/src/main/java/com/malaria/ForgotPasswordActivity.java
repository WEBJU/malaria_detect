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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText edtEmail;
    private Button btnReset;
    private TextView tvBack;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        auth=FirebaseAuth.getInstance();
        edtEmail=findViewById(R.id.email);
        btnReset=findViewById(R.id.reset);
        tvBack=findViewById(R.id.btnBack);
        progressBar=findViewById(R.id.progressBar);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
            }
        });

        btnReset.setOnClickListener(view -> {
            String email=edtEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)){
                Toast.makeText(ForgotPasswordActivity.this, "Please Enter your registered email", Toast.LENGTH_SHORT).show();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ForgotPasswordActivity.this, "We have sent an email with instructions to reset your password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ForgotPasswordActivity.this, "Something went wrong.Please check your email and try again", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });
        });
    }
}