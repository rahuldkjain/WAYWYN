package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.DailyLeaderboardPK;

import javax.persistence.*;

@Entity @IdClass(DailyLeaderboardPK.class)
@Table(name="DailyLeaderboard")
public class DailyLeaderboard {
    @Id @Column(name = "day_id") int dayId;
    @Id @Column(name = "username") String username;
    @Column(name="score") int score;

    public DailyLeaderboard(int dayId, String username, int score) {
        this.dayId = dayId;
        this.username = username;
        this.score = score;
    }

    public DailyLeaderboard() {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DailyLeaderboard{" +
                "dayId=" + dayId +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
