package com.bullsandcows.controller;

import com.bullsandcows.model.RatingUnit;
import com.bullsandcows.repository.GameHistoryRepository;
import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class RatingController {
    private final GameHistoryRepository gameHistoryRepository;

    public RatingController(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    @RequestMapping("/rating")
    public String get() {
        return "rating";
    }

    @GetMapping("/rating/topUsers")
    @ResponseBody
    public Iterable<RatingUnit> getTopUsers() {
        return gameHistoryRepository.getAllRatingUnits();
    }

    @GetMapping("/rating/myTopPosition")
    @ResponseBody
    public Pair<Integer, RatingUnit> getMyRating(Principal principal) {
        List<RatingUnit> ratingList = gameHistoryRepository.getAllRatingUnits();
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).getUsername().equals(principal.getName()))
                return new Pair<>(i + 1, ratingList.get(i));
        }
        return new Pair<>(-1, new RatingUnit(principal.getName(), 0, -1, -1));
    }
}
