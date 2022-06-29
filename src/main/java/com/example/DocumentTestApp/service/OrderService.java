package com.example.DocumentTestApp.service;

import com.example.DocumentTestApp.domain.Cooperator;
import com.example.DocumentTestApp.domain.Orders;
import com.example.DocumentTestApp.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * OrderService - сервис.
 * Служит для вывода списка поручений для определенного работника.
 * Данный сервис позволяет создать вкладки "Мои поручения" и "Поручения мне"
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Page<Orders> orderList(Pageable pageable, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return orderRepo.findBySubject(filter, pageable);
        } else {
            return orderRepo.findAll(pageable);
        }
    }

    public Page<Orders> orderListForCooperator(Pageable pageable, Cooperator currentCooperator, Cooperator author) {
        return orderRepo.findByCooperator(pageable, author);
    }

    public Page<Orders> orderListForOtherCooperator(Pageable pageable, String filter, Cooperator author) {
        return orderRepo.findByPerformer(pageable, filter);
    }


}
