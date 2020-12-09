package com.example.onlinedndproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Player;

public class LoginActivity extends AppCompatActivity {

    public Button button;
    public EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.button = (Button)findViewById(R.id.butt_login);
        this.name = (EditText) findViewById(R.id.textNickname);

        SharedPreferences sharedPref = LoginActivity.this.getPreferences(getApplicationContext().MODE_PRIVATE);
        String myName = sharedPref.getString("logged_nickname", "");
        if(!myName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Hi " + myName +", your name was filled in", Toast.LENGTH_SHORT).show();
        }
        name.setText(myName);
        name.setFocusable(true);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(name.getText().toString().isEmpty()){
                name.setError("Please fill in your nickname");
                return;
            }

                RequestHandler RH = new RequestHandler();
                //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
                MyCsvDatabase MCD = new MyCsvDatabase();

                MCD.sendContext(getApplicationContext());
                ArrayList<Player> players = RH.getGateway("players").selectAll(MCD);

                SharedPreferences sharedPref = LoginActivity.this.getPreferences(getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                for(int i=0;i<players.size();i++) {
                    String input = name.getText().toString();
                    if(input.equals(players.get(i).getNickname())){
                        if(players.get(i).getActive()==0){
                            name.setError("Im sorry, but this Player got deactivated...");
                            editor.putString("logged_nickname", "");
                            editor.apply();
                            return;
                        }
                        editor.putString("logged_nickname", input);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                        finish();
                        return;
                    }
                }
                name.setError("Player with that nickname doesn't exist, sorry...");

                editor.putString("logged_nickname", "");
                editor.apply();

            }
        });


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*intent = new Intent(this, RaceProfActivity.class);
        this.startActivity(intent);*/
    }

    @Override
    protected void onStart() {
        super.onStart();



    }
}