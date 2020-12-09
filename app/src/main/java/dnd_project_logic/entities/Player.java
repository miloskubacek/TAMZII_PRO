package dnd_project_logic.entities;

import dnd_project_logic.Entity;

public class Player extends Entity {
    private String nickname;
    private String city;
    private int role;
    private int active;
    private int player_id;

    public Player(){

    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Player(String nickname, String city, int role, int active, int player_id) {
        this.nickname = nickname;
        this.city = city;
        this.role = role;
        this.active = active;
        this.player_id = player_id;
    }
}
