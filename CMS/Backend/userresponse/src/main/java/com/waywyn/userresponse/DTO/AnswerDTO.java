package com.waywyn.userresponse.DTO;

public class AnswerDTO {
    private String answer;
    private String difficultyLevel;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                ", answer='" + answer + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}
