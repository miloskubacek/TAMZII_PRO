package com.example.onlinedndproject;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Profession;

public class NewCharActivity extends AppCompatActivity {

    public Character tmp;
    public Spinner spinnerProf;
    public Spinner spinnerRace;
    public EditText charName;
    public EditText backstory;
    public SeekBar hp;
    public SeekBar at;
    public SeekBar df;
    public Button createButt;
    public Button randomButt;
    public Button loadButt;
    public Button updateButt;

    public int tmpHp;
    public int tmpAt;
    public int tmpDf;
    public Bundle extras;
    public int player_id;

    public ArrayList<Profession> profs;
    public ArrayList<String> arrayList;

    public String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newchar);
        extras = getIntent().getExtras();
        player_id = extras.getInt("player_id");

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
        createButt = (Button)findViewById(R.id.createButt);
        randomButt = (Button)findViewById(R.id.randomButt);
        loadButt = (Button)findViewById(R.id.loadImageButt);
        updateButt = (Button)findViewById(R.id.updateButton);
        arrayList = new ArrayList<>();

        for(Profession prof : profs){
            arrayList.add(prof.getName());
        }

        /*arrayList.add("Barbarian");
        arrayList.add("Ranger");
        arrayList.add("Wizard");
        arrayList.add("Paladin");
        arrayList.add("Warrior");*/


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


        final ArrayList<String> raceList = new ArrayList<>();
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

        createButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(charName.getText().toString().isEmpty()){
                    charName.setError("Please fill out the name of your Character");
                    charName.requestFocus();
                }
                else{
                    String output;
                    output="{";
                    output+="\"char_id\": " + String.valueOf(7)
                            +"\",\"name\": " + charName.getText().toString()
                            +"\",\"fk_player_id\": " + extras.getInt("fk_player_id")
                            +"\",\"fk_race_id\": " + spinnerRace.getSelectedItem()
                            +"\",\"fk_prof_id\": " + spinnerProf.getSelectedItem()
                            +"\",\"attack\": " + at.getProgress()
                            +"\",\"health\": " + hp.getProgress()
                            +"\",\"deffence\": " + df.getProgress()
                            +"\",\"level\": " + String.valueOf(1) + "\"";
                    output+="}";

                    String input ="\n"+extras.getInt("newchar_id")+"," + charName.getText().toString() +",1," + hp.getProgress()+ "," + at.getProgress()+ "," + df.getProgress()+"," +String.valueOf(player_id) + "," +String.valueOf(spinnerRace.getSelectedItemPosition()+1)+ "," +String.valueOf(spinnerProf.getSelectedItemPosition()+1)+", ";

                    SharedPreferences sharedPref = NewCharActivity.this.getPreferences(getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(String.valueOf(extras.getInt("newchar_id"))+"_path", path);
                    editor.apply();

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    }

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    }

                    File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File dir = new File (sdCard.getAbsolutePath());
                    dir.mkdirs();
                    File file = new File(dir, "characters.csv");
                    int i=0;
                    try {
                        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(file, true));
                        csvWriter.append(input);
                        csvWriter.flush();
                        csvWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

                random = new Random().nextInt(5);
                spinnerProf.setSelection(random);
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

        updateButt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SharedPreferences sharedPref = NewCharActivity.this.getPreferences(getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(String.valueOf(extras.getInt("char_id"))+"_path", path);
                editor.apply();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==20){
                Uri imageUri = data.getData();
                path = convertMediaUriToPath(imageUri);

                InputStream is;

                try {
                    is = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(is);
                    ImageView imageView = (ImageView) findViewById(R.id.charImage);
                    imageView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String convertMediaUriToPath(Uri uri) {
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj,  null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
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

        SharedPreferences sharedPref = NewCharActivity.this.getPreferences(getApplicationContext().MODE_PRIVATE);
        String path = sharedPref.getString(String.valueOf(extras.getInt("char_id"))+"_path", "");

        InputStream is;

        try {
            //is = getContentResolver().openInputStream(imageUri);
            is = new FileInputStream(path);
            Bitmap image = BitmapFactory.decodeStream(is);
            ImageView imageView = (ImageView) findViewById(R.id.charImage);
            imageView.setImageBitmap(image);

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Image failed to load", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

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

        lockButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(extras.getInt("mode")==0){return true;}
        getMenuInflater().inflate(R.menu.menu_newchar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_update_char:
                NewCharActivity.this.unlockButtons();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void unlockButtons() {
         hp.setOnTouchListener(null);
         at.setOnTouchListener(null);
         df.setOnTouchListener(null);

        charName.setEnabled(true);
        spinnerProf.setEnabled(true);
        spinnerRace.setEnabled(true);

        loadButt.setVisibility(View.VISIBLE);
        updateButt.setVisibility(View.VISIBLE);

    }
    private void lockButtons(){
        charName.setEnabled(false);

        hp.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        at.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        df.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        loadButt.setVisibility(View.GONE);
        randomButt.setVisibility(View.GONE);
        createButt.setVisibility(View.GONE);

        TextView mainTag = (TextView)findViewById(R.id.textView4);
        mainTag.setText("CHARACTER'S SHEET");


        spinnerProf.setEnabled(false);
        spinnerRace.setEnabled(false);


    }
}
