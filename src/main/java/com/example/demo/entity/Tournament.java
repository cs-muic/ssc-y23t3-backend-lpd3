package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GameID", length = 50)
    private int gameID;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "teamname", length = 50)
    private String teamname;

    @Column(name = "tournamentid")
    private int category;

    public Tournament(String username, String teamname, int category, String name) {
        this.username = username;
        this.teamname = teamname;
        this.category = category;
        this.name = name;
    }


    public Tournament() {

    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "gameID=" + gameID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", teamname='" + teamname + '\'' +
                ", category=" + category +
                '}';
    }
}
