package com.example.onlinedndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_chars:
                intent = new Intent(this, CharsActivity.class);
                this.startActivity(intent);
                break;
            case R.id.menu_racesAndProfs:
                intent = new Intent(this, RaceProfActivity.class);
                this.startActivity(intent);
                break;

            case R.id.menu_sessions:
                intent = new Intent(this, SessionActivity.class);
                this.startActivity(intent);
                break;
            case R.id.menu_stories:
                /*intent = new Intent(this, SessionActivity.class);
                this.startActivity(intent);*/
                break;
            case R.id.menu_account:
                /*intent = new Intent(this, SessionActivity.class);
                this.startActivity(intent);*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}