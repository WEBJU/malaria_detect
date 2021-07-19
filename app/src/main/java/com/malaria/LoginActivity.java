package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail,txtPassword;
    Button btnLogin;
    TextView tvRegister,tvForgot;
    String password,email;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;

//    public static final String userEmail="";
    public static final String TAG="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        currentUser=FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser !=null){
//            startActivity(new Intent(this,MainActivity.class));
//        }

        txtEmail=findViewById(R.id.etEmail);
        txtPassword=findViewById(R.id.etPassword);
        tvRegister=findViewById(R.id.tvRegister);
        tvForgot=findViewById(R.id.forgotPassword);
        btnLogin=findViewById(R.id.btnLogin);
        progressBar=findViewById(R.id.progressBar);
        user=FirebaseAuth.getInstance().getCurrentUser();
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Log.d(TAG,"AuthStateChanged:Logout");
                }
            }
        };
    tvForgot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
        }
    });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void loginUser() {

        email=txtEmail.getText().toString().trim();
        password=txtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email cannot be blank!!", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password cannot be blank!!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Login failed!!", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.GONE);
                    checkIfEmailIsVerified();
                }
            }
        });

    }

    private void checkIfEmailIsVerified() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified=user.isEmailVerified();
        if (!emailVerified){
            Toast.makeText(this, "Your email is not verified", Toast.LENGTH_SHORT).show();
            return;
        }else{
//            txtEmail.getText().clear();
//            txtPassword.getText().clear();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("userEmail",email);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginActivity.super.finish();
    }
}