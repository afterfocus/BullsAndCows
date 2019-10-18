package com.bullsandcows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RulesController {
    @GetMapping("/rules")
    public String get() {
        return "rules";
    }
}
