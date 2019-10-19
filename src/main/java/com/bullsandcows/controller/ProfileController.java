package com.bullsandcows.controller;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.model.RatingUnit;
import com.bullsandcows.repository.GameHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ProfileController {
    private final GameHistoryRepository gameHistoryRepository;

    public ProfileController(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    @GetMapping("/profile")
    public String get() {
        return "profile";
    }

    @GetMapping("/profile/gameHistory")
    @ResponseBody
    public Iterable<GameHistory> getGameHistory(Principal principal) {
        return gameHistoryRepository.getAllByUsernameOrderByDate(principal.getName());
    }

    @GetMapping("/profile/stats")
    @ResponseBody
    public RatingUnit getStats(Principal principal) {
        RatingUnit stats = gameHistoryRepository.getRatingByUsername(principal.getName());
        if (stats == null) return new RatingUnit(principal.getName(), 0, -1, -1);
        else return stats;
    }
}
