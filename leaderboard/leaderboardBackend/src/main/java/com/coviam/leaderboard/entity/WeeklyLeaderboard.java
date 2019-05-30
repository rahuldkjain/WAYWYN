package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.WeeklyLeaderboardPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(WeeklyLeaderboardPK.class)
@Table(name="WeeklyLeaderboard")
public class WeeklyLeaderboard {
    @Id int weekId;
    @Id String username;
    int score;
    int userRank;

    public WeeklyLeaderboard() {
    }

    public WeeklyLeaderboard(int weekId, String username, int score, int userRank) {
        this.weekId = weekId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
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
}
