package com.bullsandcows.model;

import java.io.Serializable;

/**
 * Класс для передачи статистик пользователей
 */
public class UserStats implements Serializable {
    /** Имя пользователя */
    private String username;
    /** Сыграно игр */
    private long gamesPlayed;
    /** Минимальное количество попыток */
    private int bestAttempts;
    /** Среднее количество попыток */
    private double averageAttempts;

    public UserStats(String username, long gamesPlayed, int bestAttempts, double averageAttempts) {
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
