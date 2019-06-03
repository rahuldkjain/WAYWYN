package com.waywyn.userresponse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = UserContest.COLLECTION_NAME)
public class UserContest {
    public static final String COLLECTION_NAME = "usercontest";
    @Transient
    public static final String SEQUENCE_NAME = "usercontest";

    @Id
    private int ucId;
    @NotNull
    private int userId;
    @NotNull
    private String username;
    @NotNull
    private int contestId;
    @NotNull
    private boolean skipFlag;
    private Date endDate;
    @NotNull
    private String type;
    @NotNull
    private String category;

    public boolean isSkipFlag() {
        return skipFlag;
    }

    public void setSkipFlag(boolean skipFlag) {
        this.skipFlag = skipFlag;
    }

    @Override
    public String toString() {
        return "UserContest{" +
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
}
