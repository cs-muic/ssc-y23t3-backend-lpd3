package com.example.demo.DTO;

public class TournamentSaveDTO {
    private String name;
    private String username;
    private String teamname;
    private int category;

    public TournamentSaveDTO() {}

    public TournamentSaveDTO(String name, String username, String teamname, int category) {
        this.name = name;
        this.username = username;
        this.teamname = teamname;
        this.category = category;
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
        return "TournamentSaveDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", teamname='" + teamname + '\'' +
                ", category=" + category +
                '}';
    }
}
