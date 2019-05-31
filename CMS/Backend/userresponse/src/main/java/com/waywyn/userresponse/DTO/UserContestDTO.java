package com.waywyn.userresponse.DTO;

import java.util.Date;

public class UserContestDTO {
    private int ucId;
    private int userId;
    private String username;
    private int contestId;
    private boolean skipFlag = false;
    private Date endDate;
    private String type = "static";
    private String category;

    public int getUcId() {
        return ucId;
    }

    public void setUcId(int ucId) {
        this.ucId = ucId;
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

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public boolean isSkipFlag() {
        return skipFlag;
    }

    public void setSkipFlag(boolean skipFlag) {
        this.skipFlag = skipFlag;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "UserContestDTO{" +
                "ucId=" + ucId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", contestId=" + contestId +
                ", skipFlag=" + skipFlag +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
