package com.example.onlinedndproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Profession;

public class NewCharActivity extends AppCompatActivity {

    public Spinner spinnerProf;
    public Spinner spinnerRace;
    public EditText charName;
    public EditText backstory;
    public SeekBar hp;
    public SeekBar at;
    public SeekBar df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newchar);
        Bundle extras = getIntent().getExtras();

        RequestHandler RH = new RequestHandler();
        //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        MyCsvDatabase MCD = new MyCsvDatabase();

        MCD.sendContext(getApplicationContext());
        ArrayList<Profession> profs = RH.getGateway("professions").selectAll(MCD);

        spinnerProf = findViewById(R.id.ProfessionSpinner);
        ArrayList<String> arrayList = new ArrayList<>();

        for(Profession prof : profs){
            arrayList.add(prof.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProf.setAdapter(arrayAdapter);
        spinnerProf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        spinnerRace = findViewById(R.id.RaceSpinner);
        ArrayList<String> raceList = new ArrayList<>();
        raceList.add("Orc");
        raceList.add("Human");
        raceList.add("Elf");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, raceList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(arrayAdapter2);
        spinnerRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        if(extras.getInt("mode")==1){
            setValues(extras);
            charName = findViewById(R.id.CharName);
            hp = findViewById(R.id.hpRaceBar);
            at = findViewById(R.id.atRaceBar);
            df = findViewById(R.id.dfRaceBar);

            charName.setText(extras.getString("name"));

            hp.setMax(10);
            at.setMax(10);
            df.setMax(10);

            hp.setProgress(extras.getInt("health"));
            at.setProgress(extras.getInt("attack"));
            df.setProgress(extras.getInt("deffence"));

            for(Profession prof: profs){
                if(prof.getId()==extras.getInt("fk_prof_id")){
                    spinnerProf.setSelection(arrayList.indexOf(prof.getName()));
                }
            }

            spinnerRace.setSelection(extras.getInt("fk_race_id")-1);
        }
    }

    private void setValues(Bundle extras) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chars, menu);
        return true;
    }
}
