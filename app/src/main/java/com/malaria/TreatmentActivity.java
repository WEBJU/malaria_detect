package com.malaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.malaria.models.Treatment;

public class TreatmentActivity extends AppCompatActivity {
    EditText edtTitle, edtDescription;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    Button treat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        treat = findViewById(R.id.back);
        recyclerView = findViewById(R.id.list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
        fetchTreatment();
        treat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TreatmentActivity.this, InformationActivity.class));
            }
        });
    }

    private void fetchTreatment() {
        Query query = FirebaseDatabase.getInstance().getReference("Treatment");
        FirebaseRecyclerOptions<Treatment> options = new FirebaseRecyclerOptions.Builder<Treatment>().setQuery(query, new SnapshotParser<Treatment>() {
            @NonNull
            @Override
            public Treatment parseSnapshot(@NonNull DataSnapshot snapshot) {

                return new Treatment(snapshot.child("description").getValue().toString(), snapshot.child("title").getValue().toString());
            }
        }).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Treatment, VieWHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull VieWHolder vieWHolder, int i, @NonNull Treatment treatment) {
                vieWHolder.setTxtTitle(treatment.getTitle());
                vieWHolder.setTxtDescription(treatment.getDescription());
            }

            @Override
            public VieWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.treatment_list_item, parent, false);

                return new VieWHolder(view);
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }


   public   class VieWHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle, txtDescription;

        public VieWHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            txtDescription = itemView.findViewById(R.id.tvDescription);

        }

        public void setTxtTitle(String tit) {
            txtDescription.setText(tit);
        }

        public void setTxtDescription(String desc) {
            txtTitle.setText(desc);
        }
    }
}