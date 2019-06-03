package com.coviam.leaderboard.dto;

public class Winner {
    private String username;
    private Integer score;
    private Integer userRank;

    public Winner(String username, Integer score, Integer userRank) {
        this.username = username;
        this.score = score;
        this.userRank = userRank;
    }

    public Winner() {
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", userRank=" + userRank +
                '}';
    }
}
