package com.bullsandcows.repository;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.model.RatingUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameHistoryRepository extends CrudRepository<GameHistory, Long> {

    @Query("SELECT new com.bullsandcows.model.RatingUnit(history.username, COUNT(history), MIN(history.attempts), AVG(history.attempts)) " +
            "FROM GameHistory history " +
            "GROUP BY history.username " +
            "ORDER BY AVG(history.attempts) ASC")
    List<RatingUnit> getAllRatingUnits();

    @Query("SELECT new com.bullsandcows.model.RatingUnit(history.username, COUNT(history), MIN(history.attempts), AVG(history.attempts)) " +
            "FROM GameHistory history " +
            "WHERE history.username = ?1 " +
            "GROUP BY history.username")
    RatingUnit getRatingByUsername(String username);

    List<GameHistory> getAllByUsernameOrderByDate(String username);
}
