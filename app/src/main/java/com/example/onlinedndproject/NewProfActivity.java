package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewProfActivity extends AppCompatActivity {

    private TextView profName;
    private TextView profDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newprof);
        Button button = (Button)findViewById(R.id.CreateProfButton);
        profName = (TextView) findViewById(R.id.ProfName);
        profDes = (TextView) findViewById(R.id.ProfDescription);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String prof_description = extras.getString("p_prof_description");
            String prof_name = extras.getString("p_prof_name");

            profName.setText(prof_name);
            profDes.setText(prof_description);

            button.setText("Update");

        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               createNewProf();
               finish();
               Intent intent2 = new Intent(getApplicationContext(), RaceProfActivity.class);
               startActivity(intent2);
               finish();
            }

        });
    }

    private void createNewProf() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference profRef = myRef.child("professions");
        DatabaseReference child = profRef.child(String.valueOf(getIntent().getExtras().getInt("newProf_id")));


        child.child("name").setValue(profName.getText().toString());
        child.child("description").setValue(profDes.getText().toString());
        child.child("prof_id").setValue(getIntent().getExtras().getInt("newProf_id"));
    }
}
