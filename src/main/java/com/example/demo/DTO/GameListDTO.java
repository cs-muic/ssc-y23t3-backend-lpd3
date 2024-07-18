package com.example.demo.DTO;

public class GameListDTO {
    private int listID;
    private String gamename;
    private String gamelink;
    private String participantnumber;
    private String teamname;
    private int totaluser;

    public GameListDTO(int listID, String gamename, String gamelink, String participantnumber, String teamname, int totaluser) {
        this.listID = listID;
        this.gamename = gamename;
        this.gamelink = gamelink;
        this.participantnumber = participantnumber;
        this.teamname = teamname;
        this.totaluser = totaluser;
    }

    public int getTotaluser() {
        return totaluser;
    }

    public void setTotaluser(int totaluser) {
        this.totaluser = totaluser;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public GameListDTO() {
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getGamelink() {
        return gamelink;
    }

    public void setGamelink(String gamelink) {
        this.gamelink = gamelink;
    }

    public String getParticipantnumber() {
        return participantnumber;
    }

    public void setParticipantnumber(String participantnumber) {
        this.participantnumber = participantnumber;
    }

    @Override
    public String toString() {
        return "GameListDTO{" +
                "listID=" + listID +
                ", gamename='" + gamename + '\'' +
                ", gamelink='" + gamelink + '\'' +
                ", participantnumber='" + participantnumber + '\'' +
                ", teamname='" + teamname + '\'' +
                '}';
    }
}

