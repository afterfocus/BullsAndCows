package com.bullsandcows.repository;

import com.bullsandcows.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsernameIgnoringCase(String username);
}
