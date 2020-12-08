package dnd_project_logic;

import java.util.Vector;

import dnd_project_logic.entities.ProfessionGate;
import dnd_project_logic.entities.ProfessionManager;

public class RequestHandler {
    Vector<EntityManager> managers;
    Vector<EntityGateway> gateways;

    public RequestHandler() {
        managers = new Vector<EntityManager>();
        gateways = new Vector<EntityGateway>();
        managers.add(new ProfessionManager());
        gateways.add(new ProfessionGate());
    }

    public EntityManager getManager(String tableName){
        switch(tableName) {
            case "professions":
                return managers.get(0);
            default:
                return null;
        }
    }

    public EntityGateway getGateway(String tableName){
        switch(tableName) {
            case "professions":
                return gateways.get(0);
            default:
                return null;
        }
    }

}
