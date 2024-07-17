package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "game_list_database")
public class GameListDatabase {
    @Id
    @Column(name = "ListID", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ListID;

    @Column(name = "gamename", length = 50)
    private String gamename;

    @Column(name = "gamelink", length = 50)
    private String gamelink;

    @Column(name = "username", length = 50)
    private String participantnumber;

    @Column(name = "teamname", length = 50)
    private String teamname;

    @Column(name = "totaluser", length = 50)
    private int totaluser;


    public GameListDatabase(int customerID, String gamename, String link, String newpass, String teamname, int totaluser) {
        this.teamname = teamname;
        this.ListID = customerID;
        this.gamename = gamename;
        this.gamelink = link;
        this.participantnumber = newpass;
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

    public GameListDatabase() {
    }



    public GameListDatabase(String gamename, String link, String newpass, String teamname, int totaluser) {
        this.gamename = gamename;
        this.gamelink = link;
        this.participantnumber = newpass;
        this.teamname = teamname;
        this.totaluser = totaluser;
    }

    public int getListID() {
        return ListID;
    }

    public void setListID(int listID) {
        ListID = listID;
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
}
