package com.coviam.leaderboard.model;

public class UserQuestionResponse {
    private Integer qId;
    private Integer score;

    public UserQuestionResponse(Integer qId, Integer score) {
        this.qId = qId;
        this.score = score;
    }

    public UserQuestionResponse() {
    }

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
