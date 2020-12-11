package dnd_project_logic.entities;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

import dnd_project_logic.EntityGateway;
import dnd_project_logic.MyDatabase;
import dnd_project_logic.RequestHandler;

public class ProfessionGate implements EntityGateway<Profession> {

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
        Profession prof = null;

        String data = database.selectById(this.tableName, entity_id);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            ProfessionManager Man = (ProfessionManager)this.requestHandler.getManager(this.tableName);
            prof = Man.process(response);

            } catch (JSONException e) {
            e.printStackTrace();
        }

        return prof;
    }

    @Override
    public ArrayList<Profession> selectAll(@Nullable MyDatabase database){
        ArrayList<Profession> entities = new ArrayList<>();

        String data = database.selectAll(this.tableName);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            for(int i=1;i<response.length()+1;i++){
               JSONObject tmp = response.getJSONObject(String.valueOf(i));
               ProfessionManager Man = (ProfessionManager)this.requestHandler.getManager(this.tableName);
               Profession ent = Man.process(tmp);
               entities.add(ent);
            }

        }catch(JSONException pe) {
            System.out.println(pe);
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);
        return entities;
    }
}
