package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.ContestLeaderboardPK;

import javax.persistence.*;

@Entity @IdClass(ContestLeaderboardPK.class)
@Table(name="ContestLeaderboard")
public class ContestLeaderboard {

    @Id @Column(name="user_id") int userId;
    @Id @Column(name = "contest_id") int contestId;
    @Column(name = "username") String username;
    @Column(name="score") int score;

    public ContestLeaderboard(int userId, int contestId, String username, int score) {
        this.userId = userId;
        this.contestId = contestId;
        this.username = username;
        this.score = score;
    }

    public ContestLeaderboard() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
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
        return "ContestLeaderboard{" +
                "userId=" + userId +
                ", contestId=" + contestId +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
