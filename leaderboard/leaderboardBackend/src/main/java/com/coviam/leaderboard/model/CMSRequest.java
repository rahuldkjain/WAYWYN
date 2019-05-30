package com.coviam.leaderboard.model;

import java.sql.Date;
import java.util.List;

public class CMSRequest {
    public String category;
    public String type;
    public Integer contestId;
    public Date contestEndDate;
    public InternalError userId;
    public String username;
    public String endDate;
    public List questions;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Date getContestEndDate() {
        return contestEndDate;
    }

    public void setContestEndDate(Date contestEndDate) {
        this.contestEndDate = contestEndDate;
    }

    public InternalError getUserId() {
        return userId;
    }

    public void setUserId(InternalError userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List getQuestions() {
        return questions;
    }

    public void setQuestions(List questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "CMSRequest{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", contestId=" + contestId +
                ", contestEndDate=" + contestEndDate +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", endDate='" + endDate + '\'' +
                ", questions=" + questions +
                '}';
    }
}
