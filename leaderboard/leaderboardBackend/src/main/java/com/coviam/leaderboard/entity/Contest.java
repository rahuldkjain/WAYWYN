package com.coviam.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="contest")
public class Contest {
    @Id
    int contestId;
    String type;
    String category;
    Date date;

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
}
