package com.bullsandcows.controller;

import com.bullsandcows.entity.User;
import com.bullsandcows.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер для адреса "/registration"
 */
@Controller
public class RegistrationController {
    /** BCrypt-кодировщик паролей */
    private PasswordEncoder passwordEncoder;
    /** Репозиторий пользователей */
    private UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * @return возвращает страницу регистрации с пустым пользователем
     */
    @GetMapping(value = "/registration")
    public String get(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * Регистрация нового пользователя. Если пользователь с таким именем уже существует, выполняется
     * перенаправление обратно на страницу регистрации с ошибкой.
     * @param user новый пользователь
     * @return перенаправление на страницу входа в случае успешной регистрации или на страницу
     * регистрации с ошибкой, если имя пользователя уже занято.
     */
    @PostMapping(value = "/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid User user) {
        if (!createUserAccount(user)) return "redirect:/registration?usernameTaken";
        else return "redirect:/login?registered";
    }

    /**
     * Сохранение нового пользователя в репозиторий
     * @param user новый пользователь
     * @return true, если пользователь успешно сохранен или false, если пользователь с таким
     * именем уже зарегистрирован
     */
    private boolean createUserAccount(User user) {
        // Поиск пользователя с таким же именем
        if (userRepository.findByUsernameIgnoringCase(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } else return false;
    }
}
