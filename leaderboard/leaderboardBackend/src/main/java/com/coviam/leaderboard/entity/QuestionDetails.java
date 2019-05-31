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

    public QuestionDetails(int questionId, String question_text, String answer) {
        this.questionId = questionId;
        this.question_text = question_text;
        this.answer = answer;
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
}
