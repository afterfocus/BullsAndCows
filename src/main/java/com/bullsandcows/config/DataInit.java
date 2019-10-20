package com.bullsandcows.config;

import com.bullsandcows.entity.GameHistory;
import com.bullsandcows.entity.User;
import com.bullsandcows.repository.GameHistoryRepository;
import com.bullsandcows.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * Класс для заполнения in-memory базы данных
 */
@Component
public class DataInit implements ApplicationRunner {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private GameHistoryRepository gameHistoryRepository;

    @Autowired
    public DataInit(PasswordEncoder passwordEncoder, UserRepository userRepository, GameHistoryRepository gameHistoryRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.gameHistoryRepository = gameHistoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("AfterFocus");
            user.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(user);

            user = new User();
            user.setUsername("EvilYou");
            user.setPassword(passwordEncoder.encode("1234567"));
            userRepository.save(user);

            user = new User();
            user.setUsername("Tourmaline[UNO]");
            user.setPassword(passwordEncoder.encode("12345678"));
            userRepository.save(user);

            Random R = new Random();
            GameHistory history;
            for (int i = 0; i < R.nextInt(10) + 5; i++) {
                history = new GameHistory();
                history.setDate(new Date(new Date().getTime() - R.nextInt(2_000_000_000)));
                history.setUsername("AfterFocus");
                history.setAttempts(R.nextInt(15) + 6);
                gameHistoryRepository.save(history);
            }

            for (int i = 0; i < R.nextInt(15) + 20; i++) {
                history = new GameHistory();
                history.setDate(new Date(new Date().getTime() - R.nextInt(2_000_000_000)));
                history.setUsername("EvilYou");
                history.setAttempts(R.nextInt(10) + 5);
                gameHistoryRepository.save(history);
            }

            for (int i = 0; i < R.nextInt(15) + 15; i++) {
                history = new GameHistory();
                history.setDate(new Date(new Date().getTime() - R.nextInt(2_000_000_000)));
                history.setUsername("Tourmaline[UNO]");
                history.setAttempts(R.nextInt(15) + 5);
                gameHistoryRepository.save(history);
            }
        }
    }
}
