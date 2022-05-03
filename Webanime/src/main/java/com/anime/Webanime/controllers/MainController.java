package com.anime.Webanime.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("title", "Аккаунт");
        return "account";
    }
}
