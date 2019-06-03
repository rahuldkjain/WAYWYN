package com.coviam.leaderboard.dto;

public class WeeklyLeaderboardDTO {
    private int weekId;
    private String username;
    private int score;
    private int userRank;

    public WeeklyLeaderboardDTO(int weekId, String username, int score, int userRank) {
        this.weekId = weekId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
    }

    public WeeklyLeaderboardDTO() {
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
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
        return "WeeklyLeaderboardDTO{" +
                "weekId=" + weekId +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", userRank=" + userRank +
                '}';
    }
}
