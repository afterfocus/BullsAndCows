package com.bullsandcows.repository;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.model.UserStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий результатов игр
 */
@Repository
public interface GameHistoryRepository extends CrudRepository<GameHistory, Long> {

    /**
     * Получить список статистик всех пользователей, отсортированный по возрастанию среднего количества ходов
     * @return список статистик всех пользователей
     */
    @Query("SELECT new com.bullsandcows.model.UserStats(history.username, COUNT(history), MIN(history.attempts), AVG(history.attempts)) " +
            "FROM GameHistory history " +
            "GROUP BY history.username " +
            "ORDER BY AVG(history.attempts) ASC")
    List<UserStats> getAllRatingUnits();

    /**
     * Получить статистику пользователя
     * @param username имя пользователя
     * @return стаистика пользотваеля
     */
    @Query("SELECT new com.bullsandcows.model.UserStats(history.username, COUNT(history), MIN(history.attempts), AVG(history.attempts)) " +
            "FROM GameHistory history " +
            "WHERE history.username = ?1 " +
            "GROUP BY history.username")
    UserStats getRatingByUsername(String username);

    /**
     * Получить историю игр пользователя, отсортированную по возрастанию даты и времени
     * @param username имя пользователя
     * @return список результатов игр пользователя
     */
    List<GameHistory> getAllByUsernameOrderByDate(String username);
}
