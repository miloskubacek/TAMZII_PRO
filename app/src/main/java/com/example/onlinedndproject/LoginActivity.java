package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*RequestHandler RH = new RequestHandler();
                MyFirebaseDatabase MFD = new MyFirebaseDatabase();
                /*Profession prof = new Profession();
                prof.setId(6);
                prof.setName("Hobit");
                prof.setDescription("Not Prof, just test");*/

                //RH.getGateway("professions").update(prof, MFD);
               // System.out.println(RH.getGateway("professions").selectById(3, MFD));
               //System.out.println(RH.getGateway("professions").selectAll(MFD));



                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference tableRef = myRef.child("players");
                tableRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            Player player = data.getValue(Player.class);
                            System.out.println("Database "+player.getNickname());
                            String input = name.getText().toString();
                            System.out.println("Input "+input);
                            System.out.println(player.getNickname()==input);
                            //if(input=="Milosh"){
                            if(true){
                                System.out.println("Inside");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }
                            int i=0;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


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