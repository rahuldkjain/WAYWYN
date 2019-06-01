package com.waywyn.userresponse.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = UserContest.COLLECTION_NAME)
public class DynamicTimeTrack {
    public static final String COLLECTION_NAME = "dynamictimetrack";
    private int contestId;
    private int questionId;
    private Date startTime;
    private Date endTime;
    private String answer;
    private String difficulty;
    private Boolean resultDone;

    @Override
    public String toString() {
        return "DynamicTimeTrack{" +
                "contestId=" + contestId +
                ", questionId=" + questionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", answer='" + answer + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", resultDone=" + resultDone +
                '}';
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getResultDone() {
        return resultDone;
    }

    public void setResultDone(Boolean resultDone) {
        this.resultDone = resultDone;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
