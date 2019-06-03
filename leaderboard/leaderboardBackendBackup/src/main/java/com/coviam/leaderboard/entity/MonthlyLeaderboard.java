package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.MonthlyLeaderboardPK;

import javax.persistence.*;

@Entity @IdClass(MonthlyLeaderboardPK.class)
@Table(name="MonthlyLeaderboard")
public class MonthlyLeaderboard {
    @Id
    @Column(name = "month_id")
    int monthId;
    @Id
    @Column(name="username")
    String username;
    @Column(name="score")
    int score;

    public MonthlyLeaderboard(int monthId, String username, int score) {
        this.monthId = monthId;
        this.username = username;
        this.score = score;
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

    @Override
    public String toString() {
        return "MonthlyLeaderboard{" +
                "monthId=" + monthId +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
