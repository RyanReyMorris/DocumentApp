package com.example.DocumentTestApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebApplication - класс запуска приложений.
 * Будет запускать всё приложение благодаря аннотации @SpringBootApplication.
 * В папке db.migration имеются два файла sql, необходимых для создания БД, а также добавления администратора.
 * В папке templates расположены основные html документы формата ftlh (использовался шаблонизатор Freemarker).
 * В папке templates/parts расположены основные макросы для шаблонизатора.
 */
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}