package com.coviam.leaderboard.pkclasses;

import java.io.Serializable;

public class MonthlyLeaderboardPK implements Serializable {
    private int monthId;
    private String username;

    public MonthlyLeaderboardPK(int monthId, String username) {
        this.monthId = monthId;
        this.username = username;
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

}
