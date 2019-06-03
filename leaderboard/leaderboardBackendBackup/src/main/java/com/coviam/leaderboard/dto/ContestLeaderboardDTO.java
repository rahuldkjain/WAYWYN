package com.coviam.leaderboard.dto;

public class ContestLeaderboardDTO {
    private int userId;
    private int contestId;
    private String username;
    private int score;
    private int userRank;

    public ContestLeaderboardDTO(int userId, int contestId, String username, int score, int userRank) {
        this.userId = userId;
        this.contestId = contestId;
        this.username = username;
        this.score = score;
        this.userRank = userRank;
    }

    public ContestLeaderboardDTO() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "ContestLeaderboardDTO{" +
                "userId=" + userId +
                ", contestId=" + contestId +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", userRank=" + userRank +
                '}';
    }
}
