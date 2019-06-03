package com.coviam.leaderboard.dto;

public class DailyLeaderboardDTO {
    private int dayId;
    private String username;
    private int score;
    private int userRank;


    public DailyLeaderboardDTO() {
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "DailyLeaderboardDTO{" +
                "dayId=" + dayId +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", userRank=" + userRank +
                '}';
    }
}
