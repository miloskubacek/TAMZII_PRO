package dnd_project_logic.entities;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

import dnd_project_logic.EntityGateway;
import dnd_project_logic.MyDatabase;
import dnd_project_logic.RequestHandler;

public class CharacterGate implements EntityGateway<Character> {

    public String tableName = "characters";
    public RequestHandler requestHandler;

    @Override
    public void update(Character player, @Nullable MyDatabase database) {
        /*String tmpJson;
        tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);*/
    }

    @Override
    public void insert(Character player, @Nullable MyDatabase database) {
        /*String tmpJson;
        tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);*/
    }

    @Override
    public Character selectById(int entity_id, @Nullable MyDatabase database) {
        Character character = null;

        String data = database.selectById(this.tableName, entity_id);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            CharacterManager Man = (CharacterManager)this.requestHandler.getManager(this.tableName);
            character = Man.process(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);

        return character;
    }

    @Override
    public ArrayList<Character> selectAll(@Nullable MyDatabase database){
        ArrayList<Character> entities = new ArrayList<>();

        String data = database.selectAll(this.tableName);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            for(int i=1;i<response.length()+1;i++){
                JSONObject tmp = response.getJSONObject(String.valueOf(i));
                CharacterManager Man = (CharacterManager)this.requestHandler.getManager(this.tableName);
                Character ent = Man.process(tmp);
                entities.add(ent);
            }

        }catch(JSONException pe) {
            System.out.println(pe);
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);
        return entities;
    }
}