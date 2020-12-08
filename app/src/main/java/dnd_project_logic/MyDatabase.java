package dnd_project_logic;

public interface MyDatabase {
    public void update(String table, int entity_id, String data);
    public void insert(String table, String data);

    public String selectById(String table, int entity_id);
    public String selectAll(String table);
}
