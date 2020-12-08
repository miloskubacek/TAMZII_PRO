package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Vector;

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

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference profRef = myRef.child("characters");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Character> arrayList = new ArrayList<Character>();
                Vector<String> tmpVec = new Vector<String>();
                System.out.println(dataSnapshot.toString());
                for (DataSnapshot subSnapshot: dataSnapshot.getChildren()) {
                    tmpVec.clear();
                    for (DataSnapshot subSnapshot2: subSnapshot.getChildren()) {
                        tmpVec.add(subSnapshot2.getValue().toString());
                    }
                    tmpCharacter.setName(tmpVec.get(9));
                    tmpCharacter.setChar_id(Integer.parseInt(tmpVec.get(2)));
                    tmpCharacter.setLevel(Integer.parseInt(tmpVec.get(8)));
                    tmpCharacter.setFk_prof_id(Integer.parseInt(tmpVec.get(5)));
                    tmpCharacter.setFk_race_id(Integer.parseInt(tmpVec.get(6)));

                    arrayList.add(tmpCharacter);
                }
                CharViewAdapter arrayAdapter = new CharViewAdapter(getApplicationContext(), R.layout.character_adapter_view, arrayList);
                charsList.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        profRef.addValueEventListener(postListener);

        /*charsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), NewCharActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                finish();
            }
        });*/


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