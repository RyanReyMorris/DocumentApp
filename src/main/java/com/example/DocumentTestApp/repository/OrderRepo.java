package com.example.DocumentTestApp.repository;

import com.example.DocumentTestApp.domain.Cooperator;
import com.example.DocumentTestApp.domain.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * OrderRepo - репозиторий позволяет получить полный список полей поручения или же найти их по Id.
 * метод findByCooperator позволяет получить список всех поручений для данного пользователя
 * метод findBySubject позволяет получить список всех поручений по предмету поручения (атрибутивный поиск по всем
 * параметрам не настроен)
 * метод findByPerformer позволяет получить список всех поручений по их исполнителю.
 */
public interface OrderRepo extends CrudRepository<Orders, Long> {

    Page<Orders> findAll(Pageable pageable);

    Page<Orders> findBySubject(String subject, Pageable pageable);

    @Query("from Orders o where o.author =:author")
    Page<Orders> findByCooperator(Pageable pageable, @Param("author") Cooperator author);

    @Query("from Orders o where o.performer =:performer")
    Page<Orders> findByPerformer(Pageable pageable, @Param("performer") String performer);

}
