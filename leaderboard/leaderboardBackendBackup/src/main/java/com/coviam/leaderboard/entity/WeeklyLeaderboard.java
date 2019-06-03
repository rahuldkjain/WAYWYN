package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.WeeklyLeaderboardPK;

import javax.persistence.*;

@Entity @IdClass(WeeklyLeaderboardPK.class)
@Table(name="WeeklyLeaderboard")
public class WeeklyLeaderboard {
    @Id @Column(name="week_id") int weekId;
    @Id @Column(name="username") String username;
    @Column(name="score") int score;

    public WeeklyLeaderboard() {
    }

    public WeeklyLeaderboard(int weekId, String username, int score) {
        this.weekId = weekId;
        this.username = username;
        this.score = score;
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

    @Override
    public String toString() {
        return "WeeklyLeaderboard{" +
                "weekId=" + weekId +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
