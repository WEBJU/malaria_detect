package com.malaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnIdentify,btnTreat,btnFeedback,btnInformation,btnLogout,btnPatient;
    TextView username;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btnIdentify = findViewById(R.id.btn_identify);
         btnTreat=findViewById(R.id.btn_treat);
         btnPatient=findViewById(R.id.btn_patient);
         btnFeedback=findViewById(R.id.btn_feedback);
         btnInformation=findViewById(R.id.btn_inform);
         btnLogout=findViewById(R.id.btnLogout);
         username=findViewById(R.id.username);
         name=getIntent().getStringExtra("userEmail");
         username.setText("Welcome to Malaria Detect..");
         btnPatient.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,PatientDetailsActivity.class);
                 startActivity(intent);
                 overridePendingTransition(R.anim.slide_left_entering,R.anim.slide_left_exiting);
             }
         });
         btnIdentify.setOnClickListener(view -> {
           Intent intent = new Intent(MainActivity.this, ClassifierActivity.class);
           startActivity(intent);
           overridePendingTransition(R.anim.slide_left_entering,R.anim.slide_left_exiting);
           finish();
         });
        btnLogout.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
        btnInformation.setOnClickListener(this);
        btnTreat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view ==btnLogout){
            Logout();
        }
        else if(view==btnTreat){
            Treat();
        }
       else if(view==btnFeedback){
            Feedback();
        }
      else if (view==btnInformation){
            Information();
        }
    }

    private void Information() {
        startActivity(new Intent(this,UserDetailsActivity.class));
    }

    private void Feedback() {
        startActivity(new Intent(this,FeedbackActivity.class));
    }

    private void Treat() {
        startActivity(new Intent(this,TreatmentActivity.class));
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "You're now logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));

    }
}
