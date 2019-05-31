package com.waywyn.userresponse.DTO;

public class UserResultDTO {

    private int totalScore;
    private int correctAnswers;

    @Override
    public String toString() {
        return "UserResultDTO{" +
                "totalScore=" + totalScore +
                ", correctAnswers=" + correctAnswers +
                '}';
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
