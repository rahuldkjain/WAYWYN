package com.coviam.leaderboard.pkclasses;

import java.io.Serializable;

public class UserScorePK implements Serializable {
    public Integer userId;
    public Integer contestId;

    public UserScorePK(Integer userId, Integer contestId) {
        this.userId = userId;
        this.contestId = contestId;
    }

    public UserScorePK() {
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

}
