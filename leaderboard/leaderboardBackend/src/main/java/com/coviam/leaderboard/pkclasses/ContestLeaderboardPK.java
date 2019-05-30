package com.coviam.leaderboard.pkclasses;

import java.io.Serializable;

public class ContestLeaderboardPK implements Serializable {
    int userId;
    int ContestId;

    public ContestLeaderboardPK(int userId, int contestId) {
        this.userId = userId;
        ContestId = contestId;
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
        return ContestId;
    }

    public void setContestId(int contestId) {
        ContestId = contestId;
    }
}
