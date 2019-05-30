package com.coviam.leaderboard.pkclasses;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

public class DailyLeaderboardPK implements Serializable {

    int dayId;
    String username;

    public DailyLeaderboardPK(int dayId, String username) {
        this.dayId = dayId;
        this.username = username;
    }

    public DailyLeaderboardPK() {
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
}
