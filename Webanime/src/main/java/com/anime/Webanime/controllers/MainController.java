package com.anime.Webanime.controllers;

import com.anime.Webanime.entity.User;
import com.anime.Webanime.repo.UserRepository;
import com.anime.Webanime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/account")
    public String account(Model model) {
        User user = userService.getAuthorizedUser();
        model.addAttribute("user", user);
        return "account";
    }

    @GetMapping("/account/edit")
    public String accountEdit(Model model) {
        User user = userService.getAuthorizedUser();
        model.addAttribute("user", user);
        return "account-edit";
    }

    @PostMapping("/account/edit")
    public String accountEditPost(@RequestParam String username, @RequestParam(value = "file") MultipartFile file,
                                  Model model) throws IOException {
        User user = userService.getAuthorizedUser();
        user.setUsername(username);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            user.setFilename(resultFileName);
        }
        userRepository.save(user);
        return "redirect:/account";
    }
}
