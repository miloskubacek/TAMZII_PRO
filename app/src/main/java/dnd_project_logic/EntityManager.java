package dnd_project_logic;

public interface EntityManager<E extends Entity>{
    public E create(String data);
    public E update(int entity_id);
    public E process(String data);
}
