package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Character;


public class CharsActivity extends AppCompatActivity {

    ListView charsList;
    Character tmpCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chars);

        charsList = findViewById(R.id.listChars);
        tmpCharacter = new Character();

        RequestHandler RH = new RequestHandler();
        //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        MyCsvDatabase MCD = new MyCsvDatabase();

        MCD.sendContext(getApplicationContext());
        final ArrayList<Character> characters = RH.getGateway("characters").selectAll(MCD);

        CharViewAdapter arrayAdapter = new CharViewAdapter(getApplicationContext(), R.layout.character_adapter_view, characters);
        charsList.setAdapter(arrayAdapter);


        charsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Character character = characters.get(position);
                Intent intent = new Intent(getApplicationContext(), NewCharActivity.class);
                intent.putExtra("char_id", character.getChar_id());
                intent.putExtra("name", character.getName());
                intent.putExtra("fk_player_id", character.getFk_player_id());
                intent.putExtra("fk_race_id", character.getFk_race_id());
                intent.putExtra("fk_prof_id", character.getFk_prof_id());
                intent.putExtra("attack", character.getAttack());
                intent.putExtra("health", character.getHealth());
                intent.putExtra("deffence", character.getDeffence());
                intent.putExtra("level", character.getLevel());
                intent.putExtra("backstory", character.getBackstory());

                intent.putExtra("mode", 1);
                startActivity(intent);
            }
        });


        Button button = (Button)findViewById(R.id.createCharButt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewCharActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chars, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_new_char:
                Intent intent = new Intent(getApplicationContext(), NewCharActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}