package com.bullsandcows.controller;

import com.bullsandcows.entity.User;
import com.bullsandcows.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/registration")
    public String get(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors() || !createUserAccount(user)) {
            result.rejectValue("username", "Это имя пользователя уже занято");
            return "redirect:/registration?usernameTaken";
        }
        return "redirect:/login?registered";
    }

    private boolean createUserAccount(User user) {
        if (userRepository.findByUsernameIgnoringCase(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } else return false;
    }
}
