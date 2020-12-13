package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dnd_project_logic.MyCsvDatabase;
import dnd_project_logic.RequestHandler;
import dnd_project_logic.entities.Session;

public class SessionActivity extends AppCompatActivity {
    RecyclerView sessListView;
    SessViewAdapter SVA;
    ArrayList<Session> sessions;
    int sess_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        RequestHandler RH = new RequestHandler();
        //MyFirebaseDatabase MFD = new MyFirebaseDatabase();
        MyCsvDatabase MCD = new MyCsvDatabase();

        MCD.sendContext(getApplicationContext());
        sessions = RH.getGateway("sessions").selectAll(MCD);

        sess_id = sessions.size()+1;

        sessListView = findViewById(R.id.listSess);
        sessListView.setLayoutManager(new LinearLayoutManager(this));

        SVA = new SessViewAdapter(sessions, getApplicationContext(), getIntent().getExtras().getInt("player_id"));
        SVA.RC = sessListView;
        sessListView.setAdapter(SVA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sess, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        int role = getIntent().getExtras().getInt("player_role");
        if(role==0){
            menu.getItem(0).setEnabled(false);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //SecondActivity closed
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent(getApplicationContext(), SessionActivity.class)); //reload MainActivity
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_new_sess:
                Intent intent = new Intent(this, NewSessActivity.class);
                intent.putExtra("sess_id", sess_id);
                this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
