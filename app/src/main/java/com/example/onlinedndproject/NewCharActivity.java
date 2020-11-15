package com.example.onlinedndproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class NewCharActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newchar);

        Spinner spinner = findViewById(R.id.ProfessionSpinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Barbarian");
        arrayList.add("Wizard");
        arrayList.add("Rogue");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String profName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + profName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Spinner spinnerRace = findViewById(R.id.RaceSpinner);
        ArrayList<String> raceList = new ArrayList<>();
        raceList.add("Orc");
        raceList.add("Human");
        raceList.add("Elf");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, raceList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(arrayAdapter2);
        spinnerRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String raceName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + raceName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
