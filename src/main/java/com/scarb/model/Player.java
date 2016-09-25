package com.scarb.model;

public class Player {
    private Integer id;

    private Integer rank;

    private String name;

    private String clanname;

    private String clantag;

    private String profilepath;

    private String favoriterace;

    private Integer points;

    private Integer wins;

    private Integer losses;

    private String jointime;

    private String league;

    private Integer ladderid;

    private Integer winrate;

    private Integer updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClanname() {
        return clanname;
    }

    public void setClanname(String clanname) {
        this.clanname = clanname == null ? null : clanname.trim();
    }

    public String getClantag() {
        return clantag;
    }

    public void setClantag(String clantag) {
        this.clantag = clantag == null ? null : clantag.trim();
    }

    public String getProfilepath() {
        return profilepath;
    }

    public void setProfilepath(String profilepath) {
        this.profilepath = profilepath == null ? null : profilepath.trim();
    }

    public String getFavoriterace() {
        return favoriterace;
    }

    public void setFavoriterace(String favoriterace) {
        this.favoriterace = favoriterace == null ? null : favoriterace.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime == null ? null : jointime.trim();
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league == null ? null : league.trim();
    }

    public Integer getLadderid() {
        return ladderid;
    }

    public void setLadderid(Integer ladderid) {
        this.ladderid = ladderid;
    }

    public Integer getWinrate() {
        return winrate;
    }

    public void setWinrate(Integer winrate) {
        this.winrate = winrate;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdateTime(Integer updatetime) {
        this.updatetime = updatetime;
    }
}