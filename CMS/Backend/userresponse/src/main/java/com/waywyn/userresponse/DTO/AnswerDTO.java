package com.waywyn.userresponse.DTO;

public class AnswerDTO {
    private String answer;
    private String difficultyType;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "answer='" + answer + '\'' +
                ", difficultyType='" + difficultyType + '\'' +
                '}';
    }

    public String getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(String difficultyType) {
        this.difficultyType = difficultyType;
    }
}
