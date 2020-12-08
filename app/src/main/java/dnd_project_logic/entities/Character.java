package dnd_project_logic.entities;

import dnd_project_logic.Entity;

public class Character extends Entity {
    private int char_id;
    private String name;
    private int fk_player_id;
    private int fk_race_id;
    private int fk_prof_id;

    private int attack;
    private int health;
    private int deffence;
    private int level;

    private String backstory;

    public Character(int char_id, String name, int fk_player_id, int fk_race_id, int fk_prof_id, int attack, int health, int deffence, int level, String backstory) {
        this.char_id = char_id;
        this.name = name;
        this.fk_player_id = fk_player_id;
        this.fk_race_id = fk_race_id;
        this.fk_prof_id = fk_prof_id;
        this.attack = attack;
        this.health = health;
        this.deffence = deffence;
        this.level = level;
        this.backstory = backstory;
    }

    public Character() {
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFk_player_id() {
        return fk_player_id;
    }

    public void setFk_player_id(int fk_player_id) {
        this.fk_player_id = fk_player_id;
    }

    public int getFk_race_id() {
        return fk_race_id;
    }

    public void setFk_race_id(int fk_race_id) {
        this.fk_race_id = fk_race_id;
    }

    public int getFk_prof_id() {
        return fk_prof_id;
    }

    public void setFk_prof_id(int fk_prof_id) {
        this.fk_prof_id = fk_prof_id;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDeffence() {
        return deffence;
    }

    public void setDeffence(int deffence) {
        this.deffence = deffence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }
}
