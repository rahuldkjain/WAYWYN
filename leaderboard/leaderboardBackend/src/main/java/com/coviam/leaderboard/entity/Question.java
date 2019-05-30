package com.coviam.leaderboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class Question {
    @Id
    @Column(name = "questionId")
    int qId;
    int correct_count;

    public Question(int qId, int correct_count) {
        this.qId = qId;
        this.correct_count = correct_count;
    }

    public Question() {
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public int getCorrect_count() {
        return correct_count;
    }

    public void setCorrect_count(int correct_count) {
        this.correct_count = correct_count;
    }
}
