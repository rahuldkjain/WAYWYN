package com.coviam.leaderboard.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class MonthlyLeaderboardDTO {
    private int monthId;
    private String username;
    private int score;
    private int userRank;

    public MonthlyLeaderboardDTO(int monthId, String username, int score, int userRank) {
        this.monthId = monthId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
    }

    public MonthlyLeaderboardDTO() {
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
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
        return "MonthlyLeaderboardDTO{" +
                "monthId=" + monthId +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", userRank=" + userRank +
                '}';
    }
}
