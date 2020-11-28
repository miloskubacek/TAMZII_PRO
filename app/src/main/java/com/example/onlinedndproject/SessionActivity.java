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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {
    ListView sessListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("October 2nd | BRNO | Postapo |6");
        arrayList.add("December 5th | OLOMOUC | Fantasy |5");
        arrayList.add("January 17th | OSTRAVA | Postapo |3");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        sessListView = findViewById(R.id.listSessions);
        sessListView.setAdapter(arrayAdapter);
        sessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String myItem = (String) (sessListView.getItemAtPosition(position));
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
        getMenuInflater().inflate(R.menu.menu_sess, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_new_sess:
                Intent intent = new Intent(this, NewSessActivity.class);
                this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
