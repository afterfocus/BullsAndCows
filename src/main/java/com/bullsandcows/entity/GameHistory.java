package com.bullsandcows.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность базы данных для сохранения результатов игр
 */
@Entity
@Table(name = "GameHistory")
public class GameHistory {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    /** Дата и вреия окончания игры */
    @Column(name = "Date", nullable = false)
    @JsonFormat(pattern="dd.MM.yyyy - HH:mm")
    private Date date;

    /** Имя пользователя */
    @Column(name = "Username", nullable = false)
    private String username;

    /** Количество попыток */
    @Column(name = "Attempts", nullable = false)
    private int attempts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
