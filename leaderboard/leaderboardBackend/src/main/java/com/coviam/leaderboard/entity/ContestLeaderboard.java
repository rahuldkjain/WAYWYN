package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.ContestLeaderboardPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(ContestLeaderboardPK.class)
@Table(name="contest_leaderboard")
public class ContestLeaderboard {

    @Id int userId;
    @Id
    int contestId;
    String username;
    int score;
    int user_rank;

    public ContestLeaderboard(int userId, int contestId, String username, int score, int user_rank) {
        this.userId = userId;
        this.contestId = contestId;
        this.username = username;
        this.score = score;
        this.user_rank = user_rank;
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

    public int getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(int user_rank) {
        this.user_rank = user_rank;
    }
}
