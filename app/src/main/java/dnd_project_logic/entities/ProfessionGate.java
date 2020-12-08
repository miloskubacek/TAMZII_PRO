package dnd_project_logic.entities;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

import dnd_project_logic.Entity;
import dnd_project_logic.EntityGateway;
import dnd_project_logic.MyDatabase;
import dnd_project_logic.RequestHandler;

public class ProfessionGate implements EntityGateway<Profession> {

    public static Profession tmpProf = new Profession();
    public String tableName = "professions";
    public RequestHandler requestHandler;

    @Override
    public void update(Profession prof, @Nullable MyDatabase database) {
        String tmpJson;
        tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);
    }

    @Override
    public void insert(Profession prof, @Nullable MyDatabase database) {
        String tmpJson;
        tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);
    }

    @Override
    public Profession selectById(int entity_id, @Nullable MyDatabase database) {
        String data = null;
        data = database.selectById(this.tableName, entity_id);
        System.out.println("data:"+data);
        //Profession ent = this.requestHandler.getManager(this.tableName).process(data);
        //return ent;
        return null;
    }

    @Override
    public ArrayList<Profession> selectAll(@Nullable MyDatabase database){
        ArrayList<Profession> entities = new ArrayList<>();

        String data = database.selectAll(this.tableName);
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(data);
            JSONArray array = (JSONArray)obj;

            ArrayList<Object> nodes = new ArrayList<Object>();
            for (int i = 0; i < array.length(); i++){
                System.out.println((String)array.get(i));
                Entity ent = this.requestHandler.getManager(this.tableName).process((String)array.get(i));
                //entities.add(ent);
            }

        }catch(ParseException | JSONException pe) {
            System.out.println(pe);
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);
        //return entities;
        return null;
    }
}
