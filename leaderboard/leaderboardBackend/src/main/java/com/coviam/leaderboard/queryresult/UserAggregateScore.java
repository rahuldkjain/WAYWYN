package com.coviam.leaderboard.queryresult;

public class UserAggregateScore {
    Integer user_id;
    Integer score;
    String username;

    public UserAggregateScore(Integer user_id, Integer score, String username) {
        this.user_id = user_id;
        this.score = score;
        this.username = username;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAggregateScore() {
    }



    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
