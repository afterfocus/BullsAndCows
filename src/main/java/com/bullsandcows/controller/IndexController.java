package com.bullsandcows.controller;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.model.Game;
import com.bullsandcows.model.Pair;
import com.bullsandcows.repository.GameHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;

/**
 * Контроллер для адресов "/" (страница игры) и "/makeAttempt" (ходы пользователя)
 */
@Controller
public class IndexController {
    /** Коллекция игр пользователей */
    private Map<String, Game> games;
    /** Репозиторий для сохранения результатов игр */
    private GameHistoryRepository gameHistoryRepository;

    public IndexController(GameHistoryRepository gameHistoryRepository) {
        games = new HashMap<>();
        this.gameHistoryRepository = gameHistoryRepository;
    }

    /**
     * Возвращает главную страницу игры и начинает новую игру для запросившего пользователя.
     * @param principal текущий пользователь
     * @return главная страница игры
     */
    @GetMapping("/")
    public String get(Principal principal) {
        games.put(principal.getName(), new Game());
        return "index";
    }

    /**
     * Принимает число от пользователя и возвращает его оценку - число "быков" и "коров".
     * @param numbers вариант числа пользователя
     * @param principal текущий пользователь
     * @return пара <число Быков, число Коров>
     */
    @GetMapping("/makeAttempt")
    @ResponseBody
    public Pair<Integer, Integer> makeAttempt(@RequestParam(value = "numbers[]") int[] numbers, Principal principal) {
        // Находим игру, связанную с этим пользователем
        Game game = games.get(principal.getName());
        Pair<Integer, Integer> result = game.makeAttempt(numbers);
        // Если пользователь угадал число, сохраняем результат игры в репозиторий
        if (result.getFirst() == 4) {
            GameHistory history = new GameHistory();
            history.setDate(new Date());
            history.setUsername(principal.getName());
            history.setAttempts(game.getAttempts());
            gameHistoryRepository.save(history);
        }
        return result;
    }
}
