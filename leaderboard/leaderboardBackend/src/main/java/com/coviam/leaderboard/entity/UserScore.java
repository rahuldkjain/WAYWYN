package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.UserScorePK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(UserScorePK.class)
@Table(name="UserScore")
public class UserScore{
    @Id
    Integer userId;
    @Id
    Integer contestId;

    String username;
    Integer score;

    public UserScore(Integer userId, Integer contestId, String username, Integer score) {
        this.userId = userId;
        this.contestId = contestId;
        this.username = username;
        this.score = score;
    }

    public UserScore() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
