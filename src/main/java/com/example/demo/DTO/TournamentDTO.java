package com.example.demo.DTO;

public class TournamentDTO {
    private int gameID;
    private String name;
    private String username;
    private String teamname;
    private int category;

    // Default constructor
    public TournamentDTO() {
    }

    // Constructor with all fields
    public TournamentDTO(int gameID, String name, String username, String teamname, int category) {
        this.gameID = gameID;
        this.name = name;
        this.username = username;
        this.teamname = teamname;
        this.category = category;
    }

    // Getters and Setters
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
        return "TournamentDTO{" +
                "gameID=" + gameID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", teamname='" + teamname + '\'' +
                ", category=" + category +
                '}';
    }
}
