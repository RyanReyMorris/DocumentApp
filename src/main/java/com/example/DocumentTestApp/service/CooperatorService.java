package com.example.DocumentTestApp.service;

import com.example.DocumentTestApp.domain.Cooperator;
import com.example.DocumentTestApp.domain.Role;
import com.example.DocumentTestApp.repository.CooperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CooperatorService - сервис.
 * Служит для вывода списка работников, а также для
 * сохранения пользователя и обновления данных о нем в системе ADMIN'а.
 */
@Service
public class CooperatorService implements UserDetailsService {
    @Autowired
    private CooperatorRepo cooperatorRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return cooperatorRepo.findByUsername(username);
    }

    public List<Cooperator> findAll() {
        return cooperatorRepo.findAll();
    }

    public void saveUser(Cooperator cooperator, String username, Map<String, String> form) {
        cooperator.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        cooperator.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                cooperator.getRoles().add(Role.valueOf(key));
            }
        }

        cooperatorRepo.save(cooperator);
    }

    public void updateProfile(Cooperator cooperator, String password) {

        if (!StringUtils.isEmpty(password)) {
            cooperator.setPassword(password);
        }

        cooperatorRepo.save(cooperator);
    }
}



