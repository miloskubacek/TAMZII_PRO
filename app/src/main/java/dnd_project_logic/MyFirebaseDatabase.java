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

public class MyFirebaseDatabase implements MyDatabase {

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

   /* public static int getProfId(@Nullable FirebaseDatabase pDb, final Context context)
    {

        DatabaseReference myRef = pDb.getReference();
        DatabaseReference profRef = myRef.child("professions");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int tmpId = 0;
                for (DataSnapshot subSnapshot: dataSnapshot.getChildren()) {
                    int id = Integer.parseInt(subSnapshot.child("prof_id").getValue().toString());
                    if(tmpId < id ){
                        tmpId = id;
                    }
                }
                SharedPreferences app_pref = context.getSharedPreferences("DndPref", 0);
                SharedPreferences.Editor editor = app_pref.edit();
                editor.putInt("tmpId", tmpId);
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Listener Faillure");

            }
        };
        profRef.addValueEventListener(postListener);
        SharedPreferences app_pref = context.getSharedPreferences("DndPref", 0);
        return app_pref.getInt("tmpId", 0);
    }
    /*public static Vector<Profession> Select(@Nullable FirebaseDatabase pDb)
    {

    }
    public static Profession SelectById(final int profession_id, @Nullable FirebaseDatabase pDb)
    {
        /*
        DatabaseReference myRef = pDb.getReference();
        DatabaseReference profRef = myRef.child("professions");

        DatabaseReference child = profRef.child(String.valueOf(profession_id));
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Vector<String> tmpVec = new Vector<String>();
                System.out.println(dataSnapshot.toString());
                for (DataSnapshot subSnapshot: dataSnapshot.getChildren()) {
                    System.out.println(subSnapshot.getValue().toString());
                    tmpVec.add(subSnapshot.getValue().toString());
                }
                tmpProf.setDescription(tmpVec.get(0));
                tmpProf.setName(tmpVec.get(1));
                tmpProf.setId(profession_id);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
            };
        child.addValueEventListener(postListener);
        System.out.println(tmpProf.getDescription() + " + " + tmpProf.getName());
        return null;*/
