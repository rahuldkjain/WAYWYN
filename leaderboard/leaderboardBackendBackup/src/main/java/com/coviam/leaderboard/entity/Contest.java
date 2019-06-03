package com.coviam.leaderboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="Contest")
public class Contest {
    @Id
    @Column(name="contest_id") int contestId;
    @Column(name ="type") String type;
    @Column(name ="category")String category;
    @Column(name ="date")Date date;
    @Column(name ="contest_name")String contestName;

    public Contest(int contestId, String type, String category, Date date, String contestName) {
        this.contestId = contestId;
        this.type = type;
        this.category = category;
        this.date = date;
        this.contestName = contestName;
    }

    public Contest(int contestId, String type, String category, Date date) {
        this.contestId = contestId;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public Contest() {
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "contestId=" + contestId +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", contestName='" + contestName + '\'' +
                '}';
    }
}
