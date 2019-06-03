package com.coviam.leaderboard.model;

import java.sql.Date;
import java.util.List;

public class CMSStaticRequest {
    private String category;
    private String type;
    private Integer contestId;
    private Date contestEndDate;
    private Integer userId;
    private String username;
    private Date endDate;
    private boolean skip;
    private List<UserQuestionResponse> questions;

    public CMSStaticRequest() {
    }

    public CMSStaticRequest(String category, String type, Integer contestId, Date contestEndDate, Integer userId, String username, Date endDate, boolean skip, List<UserQuestionResponse> questions) {
        this.category = category;
        this.type = type;
        this.contestId = contestId;
        this.contestEndDate = contestEndDate;
        this.userId = userId;
        this.username = username;
        this.endDate = endDate;
        this.skip = skip;
        this.questions = questions;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<UserQuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UserQuestionResponse> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "CMSStaticRequest{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", contestId=" + contestId +
                ", contestEndDate=" + contestEndDate +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", endDate='" + endDate + '\'' +
                ", questions=" + questions +
                ", skip=" + skip +
                '}';
    }
}
