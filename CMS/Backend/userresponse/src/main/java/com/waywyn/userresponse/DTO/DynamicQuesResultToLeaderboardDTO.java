package com.waywyn.userresponse.DTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DynamicQuesResultToLeaderboardDTO {

    private String category;
    private String type;
    private int contestId;
    private int questionID;
    private Date date;
    private ArrayList<DynamicResponseDTO> repsonse;

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

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public Date getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.format(date);
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<DynamicResponseDTO> getRepsonse() {
        return repsonse;
    }

    public void setRepsonse(ArrayList<DynamicResponseDTO> repsonse) {
        this.repsonse = repsonse;
    }

    @Override
    public String toString() {
        return "DynamicQuesResultToLeaderboardDTO{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", contestId=" + contestId +
                ", questionID=" + questionID +
                ", date=" + date +
                ", repsonse=" + repsonse +
                '}';
    }
}
