package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Character;


public class CharsActivity extends AppCompatActivity {

    RecyclerView charsList;
    CharViewAdapter CVA;
    ArrayList<Character> characters;
    int newCharId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chars);



        RequestHandler RH = new RequestHandler();
        //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        MyCsvDatabase MCD = new MyCsvDatabase();

        MCD.sendContext(getApplicationContext());
        characters = RH.getGateway("characters").selectAll(MCD);
        int played_id = getIntent().getExtras().getInt("player_id");


        for(int i=0;i<characters.size();i++){
            Character charr = characters.get(i);
            if(charr.getFk_player_id()!=played_id){
               characters.remove(characters.indexOf(charr));
               i--;
            }
            if(newCharId<charr.getId()){
                newCharId=charr.getId();
            }
        }

        newCharId++;


        charsList = findViewById(R.id.listChars);
        charsList.setLayoutManager(new LinearLayoutManager(this));

        CVA = new CharViewAdapter(characters, getApplicationContext());
        CVA.RC = charsList;
        charsList.setAdapter(CVA);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                characters.remove(position);
                CVA.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(charsList);


        Button button = (Button)findViewById(R.id.createCharButt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewCharActivity.class);
                intent.putExtra("mode", 0);
                intent.putExtra("player_id", getIntent().getExtras().getInt("player_id"));
                intent.putExtra("newchar_id", newCharId);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //SecondActivity closed
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent(getApplicationContext(), CharsActivity.class)); //reload MainActivity
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_new_char:
                Intent intent = new Intent(getApplicationContext(), NewCharActivity.class);
                intent.putExtra("mode", 0);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}