package dnd_project_logic;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class MyTxtJsonDatabase implements MyDatabase {

    public String tmpData;
    public boolean loged;

    @Override
    public void update(String table, int entity_id, String data) {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference profRef = myRef.child(table);
        DatabaseReference child = profRef.child(String.valueOf(entity_id));
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonO = (JSONObject)parser.parse(data);

            for(Iterator iterator = jsonO.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                child.child(key).setValue(jsonO.get(key));
            }
        }catch (ParseException err){}

    }

    @Override
    public void insert(String table, String data) {
        /*DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference profRef = myRef.child(table);

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonO = (JSONObject)parser.parse(data);

            ArrayList<String> keys = (ArrayList<String>)jsonO.keys();

            DatabaseReference child = profRef.child((String)jsonO.get("prof_id"));
            for(String k: keys){
                child.child(k).setValue(jsonO.get(k));
            }
        }catch (ParseException | JSONException err){}*/

    }

    @Override
    public String selectById(String table, int entity_id){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tableRef = myRef.child(table);
        DatabaseReference child = tableRef.child(String.valueOf(entity_id));
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object tmp = dataSnapshot.getValue();
                System.out.println("tmp: "+tmp.toString());
                loged = true;
                tmpData = tmp.toString();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return null;
    }

    @Override
    public String selectAll(String table) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tableRef = myRef.child(table);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object tmp = dataSnapshot.getValue();
                tmpData = tmp.toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        tableRef.addValueEventListener(postListener);
        return tmpData;
    }
}