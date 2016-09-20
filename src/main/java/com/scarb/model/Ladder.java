package com.scarb.model;

public class Ladder {

    private Integer id;

    private String name;

    private String league;

    private String queue;

    private Integer playernum;

    private Byte iscurrent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league == null ? null : league.trim();
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue == null ? null : queue.trim();
    }

    public Integer getPlayernum() {
        return playernum;
    }

    public void setPlayernum(Integer playernum) {
        this.playernum = playernum;
    }

    public Byte getIscurrent() {
        return iscurrent;
    }

    public void setIscurrent(Byte iscurrent) {
        this.iscurrent = iscurrent;
    }
}