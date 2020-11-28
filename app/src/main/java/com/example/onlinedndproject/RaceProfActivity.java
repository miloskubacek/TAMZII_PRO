package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

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

        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("Paladin");
        arrayList2.add("Wizard");
        arrayList2.add("Barbarian");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList2);

        profListView = findViewById(R.id.listProfs);
        profListView.setAdapter(arrayAdapter2);
        profListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String myItem = (String) (profListView.getItemAtPosition(position));
                String[] splitItem = myItem.split(":");
                int itemId = Integer.parseInt(splitItem[1]);

                System.out.println(splitItem[1]);

                /*Intent intent = new Intent(getApplicationContext(), DisplayItemActivity.class);
                intent.putExtra("id", itemId);
                startActivity(intent);
                finish();*/
            }
        });
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
                /*Intent intent = new Intent(this, NewCharActivity.class);
                this.startActivity(intent);
                break;*/
            case R.id.new_Prof:
                /*Intent intent2 = new Intent(this, RaceProfActivity.class);
                this.startActivity(intent2);
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
}