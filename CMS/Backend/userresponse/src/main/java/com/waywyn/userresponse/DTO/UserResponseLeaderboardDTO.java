package com.waywyn.userresponse.DTO;

public class UserResponseLeaderboardDTO {
    private int qId;
    private int score;

    @Override
    public String toString() {
        return "UserResponseLeaderboardDTO{" +
                "qId=" + qId +
                ", score=" + score +
                '}';
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
