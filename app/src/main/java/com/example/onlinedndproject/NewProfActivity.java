package com.example.onlinedndproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dnd_project_logic.MyFirebaseDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Profession;

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
            }

        });
    }

    private void createNewProf() {
        RequestHandler RH = new RequestHandler();
        MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        Profession prof = new Profession();
        prof.setId(6);
        prof.setName("Hobit");
        prof.setDescription("Not Prof, just test");

        RH.getGateway("professions").update(prof, MFD);

    }
}
