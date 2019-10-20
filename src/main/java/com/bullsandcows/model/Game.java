package com.bullsandcows.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс игры
 */
public class Game {
    /** Коллекция с цифрами для генерации загаданного числа */
    private final List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    /** Загаданное число */
    private List<Integer> secretNumber;
    /** Количество попыток */
    private int attempts;

    public Game() {
        Collections.shuffle(numbers);
        secretNumber = numbers.subList(0, 4);
        attempts = 0;
    }

    public int getAttempts() {
        return attempts;
    }

    /**
     * Оценка предположения игрока о загадонном числе
     * @param playerNumber число - предположение игрока
     * @return пара <количество "Быков", количество "Коров">
     */
    public Pair<Integer, Integer> makeAttempt(int[] playerNumber) {
        int cows = 0;
        int bulls = 0;
        attempts++;

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(secretNumber.get(i) == playerNumber[j]) {
                    if (i == j) bulls++;
                    else cows++;
                    break;
                }

        return new Pair<>(bulls, cows);
    }
}
