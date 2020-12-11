package dnd_project_logic;

import java.util.Vector;

import dnd_project_logic.entities.CharacterGate;
import dnd_project_logic.entities.CharacterManager;
import dnd_project_logic.entities.PlayerGate;
import dnd_project_logic.entities.PlayerManager;
import dnd_project_logic.entities.ProfessionGate;
import dnd_project_logic.entities.ProfessionManager;
import dnd_project_logic.entities.SessionGate;
import dnd_project_logic.entities.SessionManager;

public class RequestHandler {
    Vector<EntityManager> managers;
    Vector<EntityGateway> gateways;

    public RequestHandler() {
        managers = new Vector<EntityManager>();
        gateways = new Vector<EntityGateway>();

        managers.add(new ProfessionManager());
        ProfessionGate PG = new ProfessionGate();
        PG.requestHandler = this;
        gateways.add(PG);

        managers.add(new PlayerManager());
        PlayerGate PLG = new PlayerGate();
        PLG.requestHandler = this;
        gateways.add(PLG);

        managers.add(new CharacterManager());
        CharacterGate ChG = new CharacterGate();
        ChG.requestHandler = this;
        gateways.add(ChG);

        managers.add(new SessionManager());
        SessionGate SG = new SessionGate();
        SG.requestHandler = this;
        gateways.add(SG);
    }

    public EntityManager getManager(String tableName){
        switch(tableName) {
            case "professions":
                return managers.get(0);
            case "players":
                return managers.get(1);
            case "characters":
                return managers.get(2);
            case "sessions":
                return managers.get(3);
            default:
                return null;
        }
    }

    public EntityGateway getGateway(String tableName){
        switch(tableName) {
            case "professions":
                return gateways.get(0);
            case "players":
                return gateways.get(1);
            case "characters":
                return gateways.get(2);
            case "sessions":
                return gateways.get(3);
            default:
                return null;
        }
    }

}
