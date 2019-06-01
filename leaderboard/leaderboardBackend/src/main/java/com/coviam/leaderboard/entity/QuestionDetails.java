package com.coviam.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QuestionDetails")
public class QuestionDetails {
    @Id
    int questionId;
    String question_text;
    String answer;
    String difficultyLevel;
    String categoryOfQuestion;
    String answerType;
    String questionType;
    String binaryFilePath;
    String optionA;
    String optionB;
    String optionC;

    public QuestionDetails(int questionId, String question_text, String answer, String difficultyLevel, String categoryOfQuestion, String answerType, String questionType, String binaryFilePath, String optionA, String optionB, String optionC) {
        this.questionId = questionId;
        this.question_text = question_text;
        this.answer = answer;
        this.difficultyLevel = difficultyLevel;
        this.categoryOfQuestion = categoryOfQuestion;
        this.answerType = answerType;
        this.questionType = questionType;
        this.binaryFilePath = binaryFilePath;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }

    public QuestionDetails() {
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

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

    public String getCategoryOfQuestion() {
        return categoryOfQuestion;
    }

    public void setCategoryOfQuestion(String categoryOfQuestion) {
        this.categoryOfQuestion = categoryOfQuestion;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getBinaryFilePath() {
        return binaryFilePath;
    }

    public void setBinaryFilePath(String binaryFilePath) {
        this.binaryFilePath = binaryFilePath;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    @Override
    public String toString() {
        return "QuestionDetails{" +
                "questionId=" + questionId +
                ", question_text='" + question_text + '\'' +
                ", answer='" + answer + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", categoryOfQuestion='" + categoryOfQuestion + '\'' +
                ", answerType='" + answerType + '\'' +
                ", questionType='" + questionType + '\'' +
                ", binaryFilePath='" + binaryFilePath + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                '}';
    }
}
