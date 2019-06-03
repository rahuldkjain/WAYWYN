package com.waywyn.userresponse.DTO;

import java.util.Date;

public class ContestDefinitionDTO {
    private int contestId;
    private String contestName;
    private int skipsAllowed;
    private int totalQuestionsInContest;
    private String contestType;
    private String categoryName;
    private String ended;

    @Override
    public String toString() {
        return "ContestDefinitionDTO{" +
                "contestId=" + contestId +
                ", contestName='" + contestName + '\'' +
                ", skipsAllowed=" + skipsAllowed +
                ", totalQuestionsInContest=" + totalQuestionsInContest +
                ", contestType='" + contestType + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", ended='" + ended + '\'' +
                '}';
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public int getSkipsAllowed() {
        return skipsAllowed;
    }

    public void setSkipsAllowed(int skipsAllowed) {
        this.skipsAllowed = skipsAllowed;
    }

    public int getTotalQuestionsInContest() {
        return totalQuestionsInContest;
    }

    public void setTotalQuestionsInContest(int totalQuestionsInContest) {
        this.totalQuestionsInContest = totalQuestionsInContest;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
