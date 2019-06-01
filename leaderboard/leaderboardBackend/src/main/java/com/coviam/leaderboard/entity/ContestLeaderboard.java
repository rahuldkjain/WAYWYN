package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.ContestLeaderboardPK;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(ContestLeaderboardPK.class)
@Table(name="ContestLeaderboard")
public class ContestLeaderboard {

    @Id int userId;
    @Id int contestId;
    String username;
    int score;
    int userRank;

    public ContestLeaderboard(int userId, int contestId, String username, int score, int userRank) {
        this.userId = userId;
        this.contestId = contestId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
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

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }
}
