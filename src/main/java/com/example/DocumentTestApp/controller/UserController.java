package com.example.DocumentTestApp.controller;

import com.example.DocumentTestApp.domain.Cooperator;
import com.example.DocumentTestApp.domain.Role;
import com.example.DocumentTestApp.service.CooperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * UserController - контроллер, ответственный за регистрацию пользователей.
 * Используется для вывода списка пользователей
 */
@Controller
@RequestMapping("/cooperator")
public class UserController {
    @Autowired
    private CooperatorService cooperatorService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("cooperators", cooperatorService.findAll());

        return "userList"; //4 отметка
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{cooperator}")
    public String userEditForm(@PathVariable Cooperator cooperator, Model model) {
        model.addAttribute("cooperator", cooperator);
        model.addAttribute("roles", Role.values());

        return "userEdit"; //3 отметка
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("cooperatorId") Cooperator cooperator
    ) {
        cooperatorService.saveUser(cooperator, username, form);

        return "redirect:/cooperator"; //2 отметка
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal Cooperator cooperator) {
        model.addAttribute("username", cooperator.getUsername());
        //все что ниже - новьё
        model.addAttribute("name", cooperator.getName());
        model.addAttribute("patronymic", cooperator.getPatronymic());
        model.addAttribute("position", cooperator.getPosition());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal Cooperator cooperator,
            @RequestParam String password
    ) {
        cooperatorService.updateProfile(cooperator, password);
        return "redirect:/cooperator/profile";
    }

}
