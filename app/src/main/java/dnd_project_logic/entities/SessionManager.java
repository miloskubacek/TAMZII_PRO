package dnd_project_logic.entities;

import org.json.JSONException;
import org.json.JSONObject;

import dnd_project_logic.EntityManager;

public class SessionManager implements EntityManager<Session> {
    @Override
    public Session create(String data) {
        return null;
    }

    @Override
    public Session update(int entity_id) {
        return null;
    }

    @Override
    public Session process(String data) {
        return null;
    }

    public Session process(JSONObject tmp) {
        try {
            Session session = new Session(Integer.parseInt(tmp.getString("session_id")),tmp.getString("date"),tmp.getString("city"), Integer.parseInt(tmp.getString("player_cappacity")), Integer.parseInt(tmp.getString("fk_story_id")));
            return session;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
