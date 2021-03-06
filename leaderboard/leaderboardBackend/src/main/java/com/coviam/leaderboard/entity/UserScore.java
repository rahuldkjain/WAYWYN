package com.coviam.leaderboard.entity;

import com.coviam.leaderboard.pkclasses.UserScorePK;

import javax.persistence.*;
import java.sql.Date;

@Entity @IdClass(UserScorePK.class)
@Table(name="UserScore")
public class UserScore{
    @Id
    @Column(name="user_id") Integer userId;
    @Id
    @Column(name="contest_id") Integer contestId;

    @Column(name="username") String username;
    @Column(name="score") Integer score;
    @Column(name = "user_end_date") Date userEndDate;


    public UserScore(Integer userId, Integer contestId, String username, Integer score, Date userEndDate) {
        this.userId = userId;
        this.contestId = contestId;
        this.username = username;
        this.score = score;
        this.userEndDate = userEndDate;
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

    public Date getUserEndDate() {
        return userEndDate;
    }

    public void setUserEndDate(Date userEndDate) {
        this.userEndDate = userEndDate;
    }
}
