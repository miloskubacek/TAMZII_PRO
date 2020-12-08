package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RaceProfActivity extends AppCompatActivity {


    ListView raceListView;
    ListView profListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races_profs);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Orc");
        arrayList.add("Elf");
        arrayList.add("Human");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        raceListView = findViewById(R.id.listRaces);
        raceListView.setAdapter(arrayAdapter);
        raceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String myItem = (String) (raceListView.getItemAtPosition(position));
                String[] splitItem = myItem.split(":");
                int itemId = Integer.parseInt(splitItem[1]);

                System.out.println(splitItem[1]);

                /*Intent intent = new Intent(getApplicationContext(), DisplayItemActivity.class);
                intent.putExtra("id", itemId);
                startActivity(intent);
                finish();*/
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference profRef = myRef.child("professions");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> arrayList2 = new ArrayList<String>();
                for (DataSnapshot subSnapshot: dataSnapshot.getChildren()) {
                    int id = Integer.parseInt(subSnapshot.child("prof_id").getValue().toString());
                    String name = subSnapshot.child("name").getValue().toString();
                    arrayList2.add(name); //id+":" +
                }
                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList2);

                profListView = findViewById(R.id.listProfs);
                profListView.setAdapter(arrayAdapter2);
                profListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String myItem = (String) (profListView.getItemAtPosition(position));
                        String[] splitItem = myItem.split(":");
                        int itemId = Integer.parseInt(splitItem[0]);

                        /*Profession prof = ProfessionGate.selectById(itemId, database);

                        Intent intent = new Intent(getApplicationContext(), NewRaceActivity.class);
                        intent.putExtra("p_prof_name", "Barbarian");
                        intent.putExtra("p_prof_description", "jhagsjdhajshg");
                        intent.putExtra("p_prof_id", itemId);
                        getApplicationContext().startActivity(intent);*/
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Listener Faillure");

            }
        };
        profRef.addValueEventListener(postListener);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_race_prof, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_Race:
                Intent intent = new Intent(this, NewRaceActivity.class);
                this.startActivity(intent);
                break;
            case R.id.new_Prof:
                Intent intent2 = new Intent(this, NewProfActivity.class);
                this.startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}