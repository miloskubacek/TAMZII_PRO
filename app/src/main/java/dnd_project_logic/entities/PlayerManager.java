package dnd_project_logic.entities;

import org.json.JSONException;
import org.json.JSONObject;

import dnd_project_logic.EntityManager;

public class PlayerManager implements EntityManager<Player> {
    @Override
    public Player create(String data) {
        return null;
    }

    @Override
    public Player update(int entity_id) {
        return null;
    }

    @Override
    public Player process(String data) {
        return null;
    }

    public Player process(JSONObject tmp) {
        try {
            Player player = new Player(tmp.getString("nickname"),tmp.getString("city"), Integer.parseInt(tmp.getString("role")), Integer.parseInt(tmp.getString("active")),Integer.parseInt(tmp.getString("player_id")));
            return player;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
