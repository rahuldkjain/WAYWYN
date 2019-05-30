package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.MonthlyLeaderboardPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(MonthlyLeaderboardPK.class)
@Table(name="monthly_leaderboard")
public class MonthlyLeaderboard {
    @Id
    int monthId;
    @Id
    String username;
    int score;
    int userRank;

    public MonthlyLeaderboard(int monthId, String username, int score, int userRank) {
        this.monthId = monthId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
    }

    public MonthlyLeaderboard() {
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
