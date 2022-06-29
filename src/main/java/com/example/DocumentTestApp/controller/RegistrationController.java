package com.example.DocumentTestApp.controller;

import com.example.DocumentTestApp.domain.Cooperator;
import com.example.DocumentTestApp.domain.Role;
import com.example.DocumentTestApp.repository.CooperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

/**
 * RegistrationController - контроллер, ответственный за регистрацию пользователей.
 */
@Controller
public class RegistrationController {
    @Autowired
    private CooperatorRepo cooperatorRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addCooperator(Cooperator cooperator, Map<String, Object> model) {
        Cooperator cooperatorFromDb = cooperatorRepo.findByUsername(cooperator.getUsername());
        if (cooperatorFromDb != null) {
            model.put("orders", "Cooperator exists!");
            return "registration";
        }
        cooperator.setActive(true);
        cooperator.setRoles(Collections.singleton(Role.USER));
        cooperatorRepo.save(cooperator);
        return "redirect:/login";
    }
}
