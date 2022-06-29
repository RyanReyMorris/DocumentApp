package com.example.DocumentTestApp.repository;

import com.example.DocumentTestApp.domain.Cooperator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CooperatorRepo - репозиторий позволяет получить полный список полей или же найти работника по Id или фамилии
 */
public interface CooperatorRepo extends JpaRepository<Cooperator, Long> {
    Cooperator findByUsername(String username);
}
