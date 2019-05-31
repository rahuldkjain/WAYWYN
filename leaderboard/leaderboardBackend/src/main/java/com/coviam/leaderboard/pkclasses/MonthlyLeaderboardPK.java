package com.coviam.leaderboard.pkclasses;

import java.io.Serializable;

public class MonthlyLeaderboardPK implements Serializable {
    private int monthId;
    private String username;
    private int score;
    private int userRank;

    public MonthlyLeaderboardPK(int monthId, String username, int score, int userRank) {
        this.monthId = monthId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
    }

    public MonthlyLeaderboardPK() {
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
}
