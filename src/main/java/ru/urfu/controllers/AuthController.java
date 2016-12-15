package ru.urfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.urfu.entities.User;
import ru.urfu.model.interfaces.UserDao;

@Controller
public class AuthController
{
    @Autowired
    private UserDao userStorage;

    @RequestMapping("/")
    public String index(Model model)
    {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return "redirect:/feed?userId=" + user.getId() + "";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/reg")
    public String reg(Model model) {
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(Model model, @RequestParam String username, String password)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(ru.urfu.auth.SecurityConfig.byteArrayToHexString(password.getBytes()));

        userStorage.create(user);

        return "redirect:/";
    }
}
