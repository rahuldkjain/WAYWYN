package com.waywyn.userresponse.DTO;

import java.util.Date;

public class DynamicTimeTrackDTO {
    private int contestId;
    private int questionId;
    private Date startTime;
    private Date endTime;
    private String difficulty;
    private String answer;

    @Override
    public String toString() {
        return "DynamicTimeTrackDTO{" +
                "contestId=" + contestId +
                ", questionId=" + questionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", difficulty='" + difficulty + '\'' +
                ", answer='" + answer + '\'' +
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
