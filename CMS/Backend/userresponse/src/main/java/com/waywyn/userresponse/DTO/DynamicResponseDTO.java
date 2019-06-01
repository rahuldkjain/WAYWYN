package com.waywyn.userresponse.DTO;

public class DynamicResponseDTO {
    private int userId;
    private String username;
    private int score;

    @Override
    public String toString() {
        return "DynamicResponseDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
