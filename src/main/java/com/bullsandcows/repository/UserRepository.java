package com.bullsandcows.repository;

import com.bullsandcows.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий пользователей
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    /**
     * Найти пользователя по имени, игнорируя регистр
     * @param username имя пользователя
     * @return пользователь
     */
    User findByUsernameIgnoringCase(String username);
}
