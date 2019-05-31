package com.waywyn.userresponse.DTO;


public class UserResponseDTO {

    private int contestId;
    private int userId;
    private String username;
    private int questionId;
    private String response;

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "contestId=" + contestId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", questionId=" + questionId +
                ", response='" + response + '\'' +
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
