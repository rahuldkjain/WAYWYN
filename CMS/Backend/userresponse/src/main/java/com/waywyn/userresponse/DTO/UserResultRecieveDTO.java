package com.waywyn.userresponse.DTO;

public class UserResultRecieveDTO {
    private int contestId;
    private int userId;
    private String username;

    @Override
    public String toString() {
        return "UserResultRecieveDTO{" +
                "contestId=" + contestId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
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
}
