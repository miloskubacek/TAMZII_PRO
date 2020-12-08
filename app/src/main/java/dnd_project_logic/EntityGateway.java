package dnd_project_logic;

import java.util.ArrayList;

public interface EntityGateway<E extends Entity> {
    public String tableName = null;
    public void update(E entity, MyDatabase database);
    public void insert(E entity, MyDatabase database);

    public E selectById(int entity_id, MyDatabase database);
    public ArrayList<E> selectAll(MyDatabase database);

}
