package com.coviam.leaderboard.pkclasses;

import java.io.Serializable;

public class ContestLeaderboardPK implements Serializable {
    private int userId;
    private int contestId;

    public ContestLeaderboardPK(int userId, int contestId) {
        this.userId = userId;
        this.contestId = contestId;
    }

    public ContestLeaderboardPK() {
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
}
