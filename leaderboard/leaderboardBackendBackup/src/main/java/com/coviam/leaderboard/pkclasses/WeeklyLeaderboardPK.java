package com.coviam.leaderboard.pkclasses;

import java.io.Serializable;

public class WeeklyLeaderboardPK implements Serializable {
    private int weekId;
    private String username;

    public WeeklyLeaderboardPK(int weekId, String username) {
        this.weekId = weekId;
        this.username = username;
    }

    public WeeklyLeaderboardPK() {
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
}
