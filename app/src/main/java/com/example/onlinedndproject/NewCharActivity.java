package com.example.onlinedndproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

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

    public int tmpHp;
    public int tmpAt;
    public int tmpDf;

    public ArrayList<Profession> profs;
    public ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newchar);
        Bundle extras = getIntent().getExtras();

        RequestHandler RH = new RequestHandler();
        //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        MyCsvDatabase MCD = new MyCsvDatabase();

        MCD.sendContext(getApplicationContext());
        profs = RH.getGateway("professions").selectAll(MCD);

        charName = findViewById(R.id.CharName);
        hp = findViewById(R.id.hpRaceBar);
        at = findViewById(R.id.atRaceBar);
        df = findViewById(R.id.dfRaceBar);
        spinnerProf = findViewById(R.id.ProfessionSpinner);
        spinnerRace = findViewById(R.id.RaceSpinner);
        arrayList = new ArrayList<>();

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




        if(extras.getInt("mode")==0){
            setNewValues();
        }
        if(extras.getInt("mode")==1){
            setValues(extras);
        }

        final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    if (seekBar.getId() == R.id.hpRaceBar) {
                        int dif = tmpHp - progress;
                        tmpHp = progress;
                        if (dif < 0) {
                            if (tmpAt >= tmpDf) {
                                tmpAt += dif;
                                at.setProgress(tmpAt);
                            } else {
                                tmpDf += dif;
                                df.setProgress(tmpDf);
                            }
                        }
                        if (dif > 0) {
                            if (tmpAt <= tmpDf) {
                                tmpAt += dif;
                                at.setProgress(tmpAt);
                            } else {
                                tmpDf += dif;
                                df.setProgress(tmpDf);
                            }
                        }
                    }
                    if (seekBar.getId() == R.id.dfRaceBar) {
                        int dif = tmpDf - progress;
                        tmpDf = progress;
                        if (dif < 0) {
                            if (tmpAt >= tmpHp) {
                                tmpAt += dif;
                                at.setProgress(tmpAt);
                            } else {
                                tmpHp += dif;
                                hp.setProgress(tmpHp);
                            }
                        }
                        if (dif > 0) {
                            if (tmpAt <= tmpHp) {
                                tmpAt += dif;
                                at.setProgress(tmpAt);
                            } else {
                                tmpHp += dif;
                                hp.setProgress(tmpHp);
                            }
                        }
                    }
                    if (seekBar.getId() == R.id.atRaceBar) {
                        int dif = tmpAt - progress;
                        tmpAt = progress;
                        if (dif < 0) {
                            if (tmpHp >= tmpDf) {
                                tmpHp += dif;
                                hp.setProgress(tmpHp);
                            } else {
                                tmpDf += dif;
                                df.setProgress(tmpDf);
                            }
                        }
                        if (dif > 0) {
                            if (tmpHp <= tmpDf) {
                                tmpHp += dif;
                                hp.setProgress(tmpHp);
                            } else {
                                tmpDf += dif;
                                df.setProgress(tmpDf);
                            }
                        }
                    }
                }
            }
        };

        hp.setOnSeekBarChangeListener(onSeekBarChangeListener);
        at.setOnSeekBarChangeListener(onSeekBarChangeListener);
        df.setOnSeekBarChangeListener(onSeekBarChangeListener);

        Button createButt = (Button)findViewById(R.id.createButt);
        Button randomButt = (Button)findViewById(R.id.randomButt);
        Button loadButt = (Button)findViewById(R.id.loadImageButt);

        createButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(charName.getText().toString().isEmpty()){
                    charName.setError("Please fill out the name of your Character");
                    charName.requestFocus();
                }
                else{
                    finish();
                }
            }
        });

        randomButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int maxstat = 15;
                int random = new Random().nextInt(5) + 3;
                hp.setProgress(random);
                maxstat-=random;
                random = new Random().nextInt(5) + 3;
                at.setProgress(random);
                maxstat-=random;
                df.setProgress(maxstat);

                spinnerProf.setSelection(0);
                random = new Random().nextInt(profs.size());
                for(Profession prof: profs){
                    if(prof.getId()==random){
                        spinnerProf.setSelection(arrayList.indexOf(prof.getName()));
                    }
                }
                random = new Random().nextInt(3);
                spinnerRace.setSelection(random);
            }
        });

        loadButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);

                File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                String path = downloadFolder.getPath();

                Uri data = Uri.parse(path);

                photoPicker.setDataAndType(data, "image/*");

                startActivityForResult(photoPicker, 20);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==20){
                Uri imageUri = data.getData();
                InputStream is;

                try {
                    is = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(is);
                    ImageView imageView = (ImageView) findViewById(R.id.charImage);
                    imageView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Image failed to load", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
    }

    private void setNewValues() {
        hp.setMax(10);
        at.setMax(10);
        df.setMax(10);

        hp.setProgress(5);
        at.setProgress(5);
        df.setProgress(5);

        tmpHp = 5;
        tmpAt = 5;
        tmpDf = 5;
    }

    private void setValues(Bundle extras) {


        charName.setText(extras.getString("name"));

        hp.setMax(10);
        at.setMax(10);
        df.setMax(10);

        hp.setProgress(extras.getInt("health"));
        at.setProgress(extras.getInt("attack"));
        df.setProgress(extras.getInt("deffence"));

        tmpHp = extras.getInt("health");
        tmpAt = extras.getInt("attack");
        tmpDf = extras.getInt("deffence");

        for(Profession prof: profs){
            if(prof.getId()==extras.getInt("fk_prof_id")){
                spinnerProf.setSelection(arrayList.indexOf(prof.getName()));
            }
        }

        spinnerRace.setSelection(extras.getInt("fk_race_id")-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chars, menu);
        return true;
    }
}
