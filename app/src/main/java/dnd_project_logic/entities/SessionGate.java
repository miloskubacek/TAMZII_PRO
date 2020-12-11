package dnd_project_logic.entities;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

import dnd_project_logic.EntityGateway;
import dnd_project_logic.MyDatabase;
import dnd_project_logic.RequestHandler;

public class SessionGate implements EntityGateway<Session> {

    public String tableName = "sessions";
    public RequestHandler requestHandler;

    @Override
    public void update(Session session, @Nullable MyDatabase database) {
        String tmpJson;
        tmpJson="{\"city\":\""+session.getCity()+"\","
                +"\"date\":\""+session.getDate().toString()+"\","
                +"\"session_id\":\""+session.getId()+"\","
                +"\"player_cappacity:\":\""+session.getPlayer_capacity()+"\","
                +"\"fk_story_id:\":\""+session.getFk_story_id()+"\","
                +"}";
        database.update(this.tableName, session.getId(), tmpJson);
    }

    @Override
    public void insert(Session prof, @Nullable MyDatabase database) {
        String tmpJson;
        /*tmpJson="{\"name\":\""+prof.getName()+"\","
                +"\"description\":\""+prof.getDescription()+"\","
                +"\"prof_id\":\""+prof.getId()+"\","
                +"}";
        database.update(this.tableName, prof.getId(), tmpJson);*/
    }

    @Override
    public Session selectById(int entity_id, @Nullable MyDatabase database) {
        Session session = null;

        String data = database.selectById(this.tableName, entity_id);
        JSONParser parser = new JSONParser();

        try{
            JSONObject response = new JSONObject(data);

            SessionManager Man = (SessionManager)this.requestHandler.getManager(this.tableName);
            session = Man.process(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return session;
    }

    @Override
    public ArrayList<Session> selectAll(@Nullable MyDatabase database){
        ArrayList<Session> entities = new ArrayList<>();

        String data = database.selectAll(this.tableName);

        try{
            JSONObject response = new JSONObject(data);

            for(int i=1;i<response.length()+1;i++){
                JSONObject tmp = response.getJSONObject(String.valueOf(i));
                SessionManager Man = (SessionManager)this.requestHandler.getManager(this.tableName);
                Session ent = Man.process(tmp);
                entities.add(ent);
            }

        }catch(JSONException pe) {
            System.out.println(pe);
        }
        //Entity ent = this.requestHandler.getManager(this.tableName).process(data);
        return entities;
    }
}
