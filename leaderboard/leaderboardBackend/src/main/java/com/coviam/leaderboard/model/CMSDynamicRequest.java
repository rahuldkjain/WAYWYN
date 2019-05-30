package com.coviam.leaderboard.model;

import java.util.List;

public class CMSDynamicRequest {
    public String category;
    public String type;
    public Integer contestId;
    public Integer qId;
    public List response;
    public boolean skip;

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

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "CMSDynamicRequest{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", contestId=" + contestId +
                ", qId=" + qId +
                ", response=" + response +
                '}';
    }
}
