package com.bullsandcows.controller;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.model.UserStats;
import com.bullsandcows.repository.GameHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Контроллер для адресов "/profile" (профиль пользователя), "/profile/gameHistory" (история игр)
 * и "/profile/stats" (статистика пользователя)
 */
@Controller
public class ProfileController {
    /** Репозиторий с результатами игр */
    private final GameHistoryRepository gameHistoryRepository;

    public ProfileController(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    /** Возвращает страницу профиля пользователя */
    @GetMapping("/profile")
    public String get() {
        return "profile";
    }

    /**
     * Возвращает массив результатов игр пользователя в JSON-формате
     * @param principal текущий пользователь
     * @return массив результатов игр
     */
    @GetMapping("/profile/gameHistory")
    @ResponseBody
    public Iterable<GameHistory> getGameHistory(Principal principal) {
        return gameHistoryRepository.getAllByUsernameOrderByDate(principal.getName());
    }

    /**
     * Возвращает статистику пользователя (количество завершенных игр, минимальное количество ходов,
     * среднее количество ходов) в JSON-формате
     * @param principal текущий пользователь
     * @return статистика пользователя
     */
    @GetMapping("/profile/stats")
    @ResponseBody
    public UserStats getStats(Principal principal) {
        // Получить статистику пользователя из репозитория результатов игр
        UserStats stats = gameHistoryRepository.getRatingByUsername(principal.getName());
        // Если статистика не найдена (пользователь не завершил ни одной игры), вернуть пустую статистику
        if (stats == null) return new UserStats(principal.getName(), 0, -1, -1);
        else return stats;
    }
}
