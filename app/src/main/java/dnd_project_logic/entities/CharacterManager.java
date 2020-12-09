package dnd_project_logic.entities;

import org.json.JSONException;
import org.json.JSONObject;

import dnd_project_logic.EntityManager;

public class CharacterManager implements EntityManager<Character> {


    @Override
    public Character create(String data) {
            return null;
            }

    @Override
    public Character update(int entity_id) {
            return null;
            }

    @Override
    public Character process(String data){
           return null;
    }

    public Character process(JSONObject tmp) {
        try {
            Character character = new Character(
                        Integer.parseInt(tmp.getString("char_id")),
                        tmp.getString("name"),
                        Integer.parseInt(tmp.getString("fk_player_id")),
                        Integer.parseInt(tmp.getString("fk_race_id")),
                        Integer.parseInt(tmp.getString("fk_prof_id")),
                        Integer.parseInt(tmp.getString("attack")),
                        Integer.parseInt(tmp.getString("health")),
                        Integer.parseInt(tmp.getString("deffence")),
                        Integer.parseInt(tmp.getString("level")),
                        tmp.getString("backstory")
            );

            return character;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
