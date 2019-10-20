package com.bullsandcows.controller;

import com.bullsandcows.model.Pair;
import com.bullsandcows.model.UserStats;
import com.bullsandcows.repository.GameHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер для адресов "/rating" (рейтинг пользователей), "/rating/topUsers" (топ игроков)
 * и "/rating/myTopPosition" (позиция пользователя в топе)
 */
@Controller
public class RatingController {
    /** Репозиторий с результатами игр */
    private final GameHistoryRepository gameHistoryRepository;

    public RatingController(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    /** Возвращает страницу рейтинга пользователей */
    @RequestMapping("/rating")
    public String get() {
        return "rating";
    }

    /**
     * Возвращает массив статистик пользователей, отсортированный по возрастанию среднего
     * количества ходов, в JSON формате
     * @return массив статистик пользователей, отсортированный по возрастанию среднего количества ходов
     */
    @GetMapping("/rating/topUsers")
    @ResponseBody
    public Iterable<UserStats> getTopUsers() {
        return gameHistoryRepository.getAllRatingUnits();
    }

    /**
     * Возвращает позицию пользователя в рейтинге и его статистику в JSON-формате
     * @param principal текущий пользователь
     * @return пара <позиция в рейтинге, статистика>
     */
    @GetMapping("/rating/myTopPosition")
    @ResponseBody
    public Pair<Integer, UserStats> getMyRating(Principal principal) {
        // Получить статистику всех пользователей, отсортированную по возрастанию среднего количества ходов
        List<UserStats> ratingList = gameHistoryRepository.getAllRatingUnits();
        // Найти и вернуть позицию пользователя в списке и его статистику
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).getUsername().equals(principal.getName()))
                return new Pair<>(i + 1, ratingList.get(i));
        }
        // Если статистика пользователя не найдена (он не завершил еще ни одной игры), вернуть значения по умолчанию
        return new Pair<>(-1, new UserStats(principal.getName(), 0, -1, -1));
    }
}
