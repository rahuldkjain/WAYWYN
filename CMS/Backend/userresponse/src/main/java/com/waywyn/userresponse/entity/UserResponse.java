package com.waywyn.userresponse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Document(collection = UserResponse.COLLECTION_NAME)
public class UserResponse {
    public final static String COLLECTION_NAME = "userresponse";
    @Transient
    public static final String SEQUENCE_NAME = "usercontest";
    @Id
    private int urId;
    private int ucId;
    private int questionId;
    private String response;
    private int score;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Override
    public String toString() {
        return "UserResponse{" +
                "urId=" + urId +
                ", ucId=" + ucId +
                ", questionId=" + questionId +
                ", response='" + response + '\'' +
                ", score=" + score +
                ", time=" + time +
                '}';
    }

    public int getUrId() {
        return urId;
    }

    public void setUrId(int urId) {
        this.urId = urId;
    }

    public int getUcId() {
        return ucId;
    }

    public void setUcId(int ucId) {
        this.ucId = ucId;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
