package com.coviam.leaderboard.model;

import java.sql.Date;
import java.util.List;

public class CMSDynamicRequest {
    public String category;
    public String type;
    public Integer contestId;
    public Integer qId;
    public Date date;
    public List<UserDynamicResponse> response;

    public CMSDynamicRequest(String category, String type, Integer contestId, Integer qId, Date date, List<UserDynamicResponse> response) {
        this.category = category;
        this.type = type;
        this.contestId = contestId;
        this.qId = qId;
        this.date = date;
        this.response = response;
    }

    public CMSDynamicRequest() {
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

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public List<UserDynamicResponse> getResponse() {
        return response;
    }

    public void setResponse(List<UserDynamicResponse> response) {
        this.response = response;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CMSDynamicRequest{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", contestId=" + contestId +
                ", qId=" + qId +
                ", date=" + date +
                ", response=" + response +
                '}';
    }
}
