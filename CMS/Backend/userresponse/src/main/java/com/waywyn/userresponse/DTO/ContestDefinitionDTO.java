package com.waywyn.userresponse.DTO;

public class ContestDefinitionDTO {
    private int contestId;
    private String contestName;
    private int numberOfskipsAllowed;
    private int totalQuestions;
    private String contestType;
    private String categoryOfContest;

    @Override
    public String toString() {
        return "ContestDefinitionDTO{" +
                "contestId=" + contestId +
                ", contestName='" + contestName + '\'' +
                ", numberOfskipsAllowed=" + numberOfskipsAllowed +
                ", totalQuestions=" + totalQuestions +
                ", contestType='" + contestType + '\'' +
                ", categoryOfContest='" + categoryOfContest + '\'' +
                '}';
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

    public int getNumberOfskipsAllowed() {
        return numberOfskipsAllowed;
    }

    public void setNumberOfskipsAllowed(int numberOfskipsAllowed) {
        this.numberOfskipsAllowed = numberOfskipsAllowed;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getCategoryOfContest() {
        return categoryOfContest;
    }

    public void setCategoryOfContest(String categoryOfContest) {
        this.categoryOfContest = categoryOfContest;
    }
}
