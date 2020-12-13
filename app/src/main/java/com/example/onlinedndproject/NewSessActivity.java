package com.example.onlinedndproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NewSessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsess);

        Spinner spinner = findViewById(R.id.StorySpinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Postapo");
        arrayList.add("Fantasy");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String profName = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + profName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Button createSButt = (Button)findViewById(R.id.createSButt);
        createSButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText city = (EditText)findViewById(R.id.cityName);
                EditText capp = (EditText)findViewById(R.id.editPlayerCapacity);
                DatePicker date = (DatePicker)findViewById(R.id.myDatePicker);
                String dateS = String.valueOf(date.getYear()) + "/" + String.valueOf(date.getMonth()) + "/" +String.valueOf(date.getDayOfMonth());
                Spinner story = (Spinner)findViewById(R.id.StorySpinner);


                String input ="\n"+String.valueOf(getIntent().getExtras().getInt("sess_id"))+"," + city.getText().toString() + "," +dateS + "," +capp.getText().toString() + ","+String.valueOf(story.getSelectedItemPosition()+1);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                }

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                }

                File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File dir = new File (sdCard.getAbsolutePath());
                dir.mkdirs();
                File file = new File(dir, "sessions.csv");
                int i=0;
                try {
                    BufferedWriter csvWriter = new BufferedWriter(new FileWriter(file, true));
                    csvWriter.append(input);
                    csvWriter.flush();
                    csvWriter.close();
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
