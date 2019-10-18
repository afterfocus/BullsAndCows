package com.bullsandcows.controller;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.model.Game;
import com.bullsandcows.repository.GameHistoryRepository;
import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;


@Controller
public class IndexController {
    private final Game game;
    private final GameHistoryRepository gameHistoryRepository;

    public IndexController(Game game, GameHistoryRepository gameHistoryRepository) {
        this.game = game;
        this.gameHistoryRepository = gameHistoryRepository;
    }

    @GetMapping("/")
    public String get() {
        game.startNewGame();
        return "index";
    }

    @GetMapping("/makeAttempt")
    @ResponseBody
    public Pair<Integer, Integer> makeAttempt(@RequestParam(value = "numbers[]") int[] numbers, Principal principal) {
        Pair<Integer, Integer> result = game.makeAttempt(numbers);
        if (result.getKey() == 4) {
            GameHistory history = new GameHistory();
            history.setDate(new Date());
            history.setUsername(principal.getName());
            history.setAttempts(game.getAttempts());
            gameHistoryRepository.save(history);
        }
        return result;
    }
}
