package com.example.onlinedndproject;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Character;

public class CharOnSessActivity extends AppCompatActivity {

    ArrayList<Character> characters;
    ArrayList<Character> playersCharacters;
    ArrayList<String> charNames;

    Button loadButt;

    int tmpChar_id;
    int sess_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charsonsess);

        loadButt = (Button) findViewById(R.id.lockinbutt);
        loadButt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendToChOS();
            }
        });

        sess_id = getIntent().getExtras().getInt("session_id");

        RequestHandler RH = new RequestHandler();
        //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        MyCsvDatabase MCD = new MyCsvDatabase();

        Spinner spinerChar = findViewById(R.id.charsSpinner);

        ArrayList<Integer> chars_id = getCharIds(sess_id);

        MCD.sendContext(getApplicationContext());
        characters = RH.getGateway("characters").selectAll(MCD);
        int played_id = getIntent().getExtras().getInt("player_id");

        playersCharacters = RH.getGateway("characters").selectAll(MCD);;
        for(int i=0;i<playersCharacters.size();i++){
            Character charr = playersCharacters.get(i);
            if(charr.getFk_player_id()!=played_id){
                playersCharacters.remove(playersCharacters.indexOf(charr));
                i--;
            }
        }

        charNames = new ArrayList<String>();
        for(int i=0;i<playersCharacters.size();i++){
            Character charr = playersCharacters.get(i);
            charNames.add(charr.getName());
        }


        ArrayList<Character> lockedInChars = new ArrayList<Character>();

        for(int i=0;i<characters.size();i++){
            Character charr = characters.get(i);
            for(int j=0;j<chars_id.size();j++) {
                if (chars_id.get(j) == charr.getId()) {
                    lockedInChars.add(charr);
                    if(charr.getFk_player_id()==played_id){lockButtons();}

                }
            }
        }

        ArrayList<String> lockedInCharsNames = new ArrayList<String>();
        for(int i=0;i<lockedInChars.size();i++){
            Character charr = lockedInChars.get(i);
            lockedInCharsNames.add(charr.getName());
        }
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lockedInCharsNames);

        ListView charList = (ListView)findViewById(R.id.lockedinchars);
        charList.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, charNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerChar.setAdapter(arrayAdapter);
        spinerChar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<playersCharacters.size();i++){
                    Character charr = playersCharacters.get(i);
                    if(charr.getName().equals(charNames.get(position))){
                        tmpChar_id = charr.getId();
                    }
                }
                return;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

    private void lockButtons() {
        loadButt.setEnabled(false);
        ListView charList = (ListView)findViewById(R.id.lockedinchars);
        charList.setEnabled(false);
        Spinner spinerChar = findViewById(R.id.charsSpinner);
        spinerChar.setEnabled(false);
        TextView mch = (TextView)findViewById(R.id.textViewMCH);
        mch.setText("You are IN!");
    }

    private ArrayList<Integer> getCharIds(int sess_id) {
            ArrayList<Integer> charsId = new ArrayList<Integer>();

            File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File dir = new File (sdCard.getAbsolutePath());
            dir.mkdirs();
            File file = new File(dir, "charsOnSessions.csv");

            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(file));
                String output="{";
                String row = csvReader.readLine();

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if(data[1].equals(String.valueOf(sess_id))){
                        charsId.add(Integer.parseInt(data[0]));
                    }

                }
                csvReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return charsId;
    }

    public void appendToChOS(){
        addNotification();
       String input ="\n" + tmpChar_id + "," + sess_id;

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    }

    File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    File dir = new File (sdCard.getAbsolutePath());
        dir.mkdirs();
    File file = new File(dir, "charsOnSessions.csv");
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
        addNotification();
        finish();
    return ;
}

    private void addNotification() {
        String message = "Session is in " + getIntent().getExtras().getString("sess_city") + " at " + getIntent().getExtras().getString("sess_date");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");
        Intent notiIntent = new Intent(getApplicationContext(), CharOnSessActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notiIntent, 0);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.dnd_online_round);
        mBuilder.setContentTitle("You just singed in on Session");
        mBuilder.setContentText(message);
        mBuilder.setPriority(Notification.PRIORITY_DEFAULT);

        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "notify_001";
            NotificationChannel channel = new NotificationChannel(
                    channelId, "session_notifier", NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }
}
