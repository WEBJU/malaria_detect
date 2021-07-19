package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.malaria.models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PatientDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseAuth auth;
    FirebaseDatabase database;
    final Calendar dob=Calendar.getInstance();
    DatabaseReference patientRef;
    EditText etFirstName,etLastName,etMiddleName,etDOB,etMeds,etNetTreatment,etOther;
    Spinner etGender,etLocation;
    RadioGroup rgMeds,rgNet,rgFever,rgDiarrhea,rgFlu,rgYellow,rgChills,rgFatigue,rgSweat,rgSleep;
    RadioButton rbMeds,rbNet,rbFever,rbDiarrhea,rbFlu,rbYellow,rbChills,rbFatigue,rbSweat,rbSleep;
    Button btnSave;
    String [] patientLocation={"Select your Location","Mombasa","Kwale","Kilifi","Tana River",
            "Lamu","Taita/Taveta","Garissa","Wajir","Mandera","Marsabit",
            "Isiolo","Meru",
            "Tharaka-Nithi","Embu","Kitui","Machakos","Makueni",
            "Nyandarua","Nyeri","Kirinyaga","Murang'a","Kiambu","Turkana",
            "West Pokot","Samburu","Trans Nzoia","Uasin Gishu","Elgeyo/Marakwet","Nandi",
            "Baringo","Laikipia","Nakuru","Narok","Kajiado","Kericho","Bomet",
            "Kakamega","Vihiga","Bungoma","Busia","Siaya","Kisumu","Homa Bay",
            "Migori","Kisii","Nyamira","Nairobi City"};
    String [] patientGender={"Select Gender","Male","Female","Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        patientRef=database.getReference("Patients");

        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.edtLastName);
        etMiddleName=findViewById(R.id.etMiddleName);
        etDOB=findViewById(R.id.edtDOB);
        etGender=findViewById(R.id.edtGender);
        etLocation=findViewById(R.id.edtLocation);
        etMeds=findViewById(R.id.currentMeds);
        etNetTreatment=findViewById(R.id.edtNetTreatment);
        etOther=findViewById(R.id.edtOther);

//        list.add(patientLocation);
        rgMeds=findViewById(R.id.radioGroupMeds);
        rgNet=findViewById(R.id.netRadio);
        rgFever=findViewById(R.id.feverRadio);
        rgDiarrhea=findViewById(R.id.diarrheaRadio);
        rgFlu=findViewById(R.id.fluRadio);
        rgYellow=findViewById(R.id.yellowRadio);
        rgChills=findViewById(R.id.chillsRadio);
        rgFatigue=findViewById(R.id.fatigueRadio);
        rgSweat=findViewById(R.id.sweatRadio);
        rgSleep=findViewById(R.id.radioGroupMeds);

//        etLocation.setOnItemSelectedListener(this);
//        etGender.setOnItemSelectedListener(this);
        ArrayAdapter locationAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,patientLocation);
        ArrayAdapter genderAdaapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,patientGender);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        genderAdaapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        etLocation.setAdapter(locationAdapter);
        etGender.setAdapter(genderAdaapter);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedMed=rgMeds.getCheckedRadioButtonId();
                rbMeds=findViewById(selectedMed);
                int selectedNet=rgNet.getCheckedRadioButtonId();
                rbNet=findViewById(selectedNet);
                int selectedFever=rgFever.getCheckedRadioButtonId();
                rbFever=findViewById(selectedFever);
                int selectedDiarrhea=rgDiarrhea.getCheckedRadioButtonId();
                rbDiarrhea=findViewById(selectedDiarrhea);
                int selectedFlu=rgFlu.getCheckedRadioButtonId();
                rbFlu=findViewById(selectedFlu);
                int selectedYellow=rgYellow.getCheckedRadioButtonId();
                rbYellow=findViewById(selectedYellow);
                int selectedChills=rgChills.getCheckedRadioButtonId();
                rbChills=findViewById(selectedChills);
                int selectedFatigue=rgFatigue.getCheckedRadioButtonId();
                rbFatigue=findViewById(selectedFatigue);
                int selectedSweat=rgSweat.getCheckedRadioButtonId();
                rbSweat=findViewById(selectedSweat);
                int selectedSleep=rgSleep.getCheckedRadioButtonId();
                rbSleep=findViewById(selectedSleep);
                String created_at = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String uid = null;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    uid = user.getUid();
                }
                String fname, lname, mname, birthdate, gender, location, meds, net_treatment, other, selectedMedi,
                        selectedNeti, selectedFeveri, selectedDiarrheai, selectedFlui, selectedYellowi,
                        selectedChillsi, selectedFatiguei, selectedSweati, selectedSleepi;
                fname = etFirstName.getText().toString().trim();
                lname = etLastName.getText().toString().trim();
                mname = etFirstName.getText().toString().trim();
                birthdate = etDOB.getText().toString().trim();
                gender = etGender.getSelectedItem().toString();
                location = etLocation.getSelectedItem().toString();
                meds = etMeds.getText().toString().trim();
                net_treatment = etNetTreatment.getText().toString().trim();
                other = etOther.getText().toString().trim();
                selectedMedi = rbMeds.getText().toString().trim();
                selectedNeti = rbNet.getText().toString().trim();
                selectedFeveri = rbFever.getText().toString().trim();
                selectedDiarrheai = rbDiarrhea.getText().toString().trim();
                selectedFlui = rbFlu.getText().toString().trim();
                selectedYellowi = rbYellow.getText().toString().trim();
                selectedChillsi = rbChills.getText().toString().trim();
                selectedFatiguei = rbFatigue.getText().toString().trim();
                selectedSweati = rbSweat.getText().toString().trim();
                selectedSleepi = rbSleep.getText().toString().trim();
                if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(lname) || TextUtils.isEmpty(mname) ||
                        TextUtils.isEmpty(birthdate) || TextUtils.isEmpty(location) || TextUtils.isEmpty(meds)
                        || TextUtils.isEmpty(net_treatment) || TextUtils.isEmpty(other)) {
                    Toast.makeText(getApplicationContext(), "Please check for empty values and try again", Toast.LENGTH_SHORT).show();
                } else {


                    String finalUid = uid;
                    Patient patient = new Patient(fname, lname, mname, selectedChillsi, selectedFatiguei,
                            selectedFlui, selectedFeveri, selectedMedi, selectedDiarrheai, selectedNeti,
                            location, meds, finalUid, selectedSweati, selectedSleepi, net_treatment, selectedYellowi, other, birthdate, created_at);
                    patientRef.push().setValue(patient);
                    patientRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Toast.makeText(PatientDetailsActivity.this, "Patient details submitted successfully!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PatientDetailsActivity.this,MainActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(PatientDetailsActivity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
                }


                savePatientDetails();

            }
        });
//        list.add("Select Gender..");
        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                dob.set(Calendar.YEAR,year);
                dob.set(Calendar.MONTH,monthOfYear);
                dob.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }

        };



        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PatientDetailsActivity.this,date,dob.get(Calendar.YEAR),dob.get(Calendar.MONTH),dob.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    private void savePatientDetails() {
           }
    private void updateLabel() {
        String format="MM/dd/yy";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format, Locale.US);
        etDOB.setText(simpleDateFormat.format(dob.getTime()));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}