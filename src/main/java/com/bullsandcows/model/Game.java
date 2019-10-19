package com.bullsandcows.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class Game {
    private final List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private List<Integer> secretNumber;
    private int attempts;

    public int getAttempts() {
        return attempts;
    }

    public void startNewGame() {
        Collections.shuffle(numbers);
        secretNumber = numbers.subList(0, 4);
        attempts = 0;
    }

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
