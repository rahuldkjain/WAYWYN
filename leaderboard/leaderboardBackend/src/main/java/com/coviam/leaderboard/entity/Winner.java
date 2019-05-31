package com.coviam.leaderboard.entity;

public class Winner {
    public String username;
    public Integer score;
    public Integer userRank;

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
