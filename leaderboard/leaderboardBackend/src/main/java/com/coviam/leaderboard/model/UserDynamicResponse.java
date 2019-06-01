package com.coviam.leaderboard.model;

public class UserDynamicResponse {
    private Integer userId;
    private String username;
    private Integer score;

    public UserDynamicResponse(Integer userId, String username, Integer score) {
        this.userId = userId;
        this.username = username;
        this.score = score;
    }

    public UserDynamicResponse() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
