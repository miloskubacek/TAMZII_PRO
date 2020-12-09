package dnd_project_logic.entities;

        import org.json.JSONException;
        import org.json.JSONObject;

        import dnd_project_logic.EntityManager;

public class ProfessionManager implements EntityManager<Profession> {
    @Override
    public Profession create(String data) {
        return null;
    }

    @Override
    public Profession update(int entity_id) {
        return null;
    }

    @Override
    public Profession process(String data) {
        return null;
    }

    public Profession process(JSONObject tmp) {
        try {
            Profession prof = new Profession(Integer.parseInt(tmp.getString("prof_id")),tmp.getString("name"),tmp.getString("description"));
            return prof;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
