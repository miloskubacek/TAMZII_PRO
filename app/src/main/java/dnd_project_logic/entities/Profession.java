package dnd_project_logic.entities;

//import lombok.Data;

import dnd_project_logic.Entity;

//@Data
public class Profession extends Entity {
    private int id;
    private String name;
    private String description;

    public Profession(){

    }
    public Profession(int id, String name, String description) {
        this.id = id;
        this.name = name;
        description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
