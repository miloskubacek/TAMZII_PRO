package dnd_project_logic.entities;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

import dnd_project_logic.EntityGateway;
import dnd_project_logic.MyDatabase;
import dnd_project_logic.RequestHandler;

public class PlayerGate implements EntityGateway<Player> {

    public String tableName = "players";
    public RequestHandler requestHandler;

    @Override
    public void update(Player player, @Nullable MyDatabase database) {
        /*String tmpJson;
        tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);*/
    }

    @Override
    public void insert(Player player, @Nullable MyDatabase database) {
        /*String tmpJson;
        tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);*/
    }

    @Override
    public Player selectById(int entity_id, @Nullable MyDatabase database) {
        Player player = null;

        String data = database.selectById(this.tableName, entity_id);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            PlayerManager Man = (PlayerManager)this.requestHandler.getManager(this.tableName);
            player = Man.process(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);

        return player;
    }

    @Override
    public ArrayList<Player> selectAll(@Nullable MyDatabase database){
        ArrayList<Player> entities = new ArrayList<>();

        String data = database.selectAll(this.tableName);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            for(int i=1;i<response.length();i++){
                JSONObject tmp = response.getJSONObject(String.valueOf(i));
                PlayerManager Man = (PlayerManager)this.requestHandler.getManager(this.tableName);
                Player ent = Man.process(tmp);
                entities.add(ent);
            }

        }catch(JSONException pe) {
            System.out.println(pe);
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);
        return entities;
    }
}