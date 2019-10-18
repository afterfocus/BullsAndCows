package com.bullsandcows.model;

import java.io.Serializable;

public class RatingUnit implements Serializable {
    private String username;
    private long gamesPlayed;
    private int bestAttempts;
    private double averageAttempts;

    public RatingUnit(String username, long gamesPlayed, int bestAttempts, double averageAttempts) {
        this.username = username;
        this.gamesPlayed = gamesPlayed;
        this.bestAttempts = bestAttempts;
        this.averageAttempts = averageAttempts;
    }

    public String getUsername() {
        return username;
    }

    public long getGamesPlayed() {
        return gamesPlayed;
    }

    public int getBestAttempts() {
        return bestAttempts;
    }

    public double getAverageAttempts() {
        return averageAttempts;
    }
}
