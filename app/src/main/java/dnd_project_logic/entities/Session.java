package dnd_project_logic.entities;

import java.util.Date;

import dnd_project_logic.Entity;

public class Session extends Entity {
    private int session_id;
    private Date date;
    private String strDate;
    private String city;
    private int player_capacity;
    private int fk_story_id;

    public Session() {
    }

    //public Session(int session_id, Date date, String city, int player_capacity, int fk_story_id) {
    public Session(int session_id, String date, String city, int player_capacity, int fk_story_id) {
        this.session_id = session_id;
        //this.date = date;
        this.strDate = date;
        this.city = city;
        this.player_capacity = player_capacity;
        this.fk_story_id = fk_story_id;
    }

    public int getId() {
        return session_id;
    }

    public void setId(int session_id) {
        this.session_id = session_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPlayer_capacity() {
        return player_capacity;
    }

    public void setPlayer_capacity(int player_capacity) {
        this.player_capacity = player_capacity;
    }

    public int getFk_story_id() {
        return fk_story_id;
    }

    public void setFk_story_id(int fk_story_id) {
        this.fk_story_id = fk_story_id;
    }
}
