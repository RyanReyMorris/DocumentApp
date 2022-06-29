package com.example.DocumentTestApp.controller;

import com.example.DocumentTestApp.domain.Cooperator;
import com.example.DocumentTestApp.domain.Orders;
import com.example.DocumentTestApp.repository.OrderRepo;
import com.example.DocumentTestApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * MainController - программный модуль, который слушает запросы от пользователя и возврщает определенные данные.
 * В основном возвращает файл шаблона.Реализует основную логику веб-приложения.
 */
@Controller
public class MainController {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;
    /**
     * uploadPath - необходим для загрузки фотографий в дополнение к поручениям
     */
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "home";
    }

    /**
     * Обрабатывает get-запросы.
     * Метод позволяет выводить список всех сообщений в зависимости от наличия фильтра
     */
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "")
                       String filter, Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Orders> page = orderService.orderList(pageable, filter);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);
        return "main";
    }

    /**
     * Обрабатывает post-запросы.
     * Метод обрабатывать запрос пользователя на создание нового поручения.
     */
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal Cooperator cooperator,
            @Valid Orders order,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        order.setAuthor(cooperator);
        saveFile(order, file);
        model.addAttribute("order", null);
        orderRepo.save(order);

        model.addAttribute("url", "/main");
        Page<Orders> page = this.orderService.orderList(pageable, ""); // пустой фильтр
        model.addAttribute("page", page);
        return "main";
    }

    /**
     * Метод позволяет прикреплять файлы к поручению.
     */
    private void saveFile(Orders order, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            order.setFilename(resultFilename);
        }
    }

    /**
     * Данный Get позволяет реализовать вкладку "Мои поручения"
     */
    @GetMapping("/cooperator-orders/{author}")
    public String cooperatorOrders(
            @AuthenticationPrincipal Cooperator currentCooperator,
            @PathVariable Cooperator author,
            Model model,
            @RequestParam(required = false) Orders order,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Orders> page = orderService.orderListForCooperator(pageable, currentCooperator, author);

        model.addAttribute("page", page);
        model.addAttribute("order", order);
        model.addAttribute("isCurrentCooperator", currentCooperator.equals(author));
        model.addAttribute("url", "/cooperator-orders" + author.getId());

        return "cooperatorOrders";
    }

    /**
     * Данный Post позволяет изменять данные поручения.
     */
    @PostMapping("/cooperator-orders/{cooperator}")
    public String updateOrder(
            @AuthenticationPrincipal Cooperator currentCooperator,
            @PathVariable Long cooperator,
            @RequestParam("id") Orders order,
            @RequestParam("subject") String subject,
            @RequestParam("performer") String performer,
            @RequestParam("deadline") String deadline,
            @RequestParam("controlling") String controlling,
            @RequestParam("performing") String performing,
            @RequestParam("text") String text,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (order.getAuthor().equals(currentCooperator)) {
            if (!StringUtils.isEmpty(subject)) {
                order.setSubject(subject);
            }
            if (!StringUtils.isEmpty(performer)) {
                order.setPerformer(performer);
            }
            if (!StringUtils.isEmpty(deadline)) {
                order.setDeadline(deadline);
            }
            if (!StringUtils.isEmpty(controlling)) {
                order.setControlling(controlling);
            }
            if (!StringUtils.isEmpty(performing)) {
                order.setPerforming(performing);
            }
            if (!StringUtils.isEmpty(text)) {
                order.setText(text);
            }

            saveFile(order, file);

            orderRepo.save(order);
        }

        return "redirect:/cooperator-orders/" + cooperator;
    }

    /**
     * Данный Get позволяет удалить поручение из БД
     */
    @GetMapping("/del-cooperator-orders/{cooperator}")
    public String deleteOrder(
            @PathVariable Long cooperator,
            @RequestParam("order") Long orderId
    ) throws IOException {
        orderRepo.deleteById(orderId);
        return "redirect:/cooperator-orders/" + cooperator;
    }

    /**
     * Данный Get позволяет реализовать вкладку "Поручения мне"
     */
    @GetMapping("/cooperator-other-orders/{author}")
    public String cooperatorOtherOrders(
            @AuthenticationPrincipal Cooperator currentCooperator,
            @PathVariable Cooperator author,
            Model model,
            @RequestParam(required = false) Orders order,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {

        Page<Orders> page = orderService.orderListForOtherCooperator(pageable, currentCooperator.getUsername(), author);
        model.addAttribute("page", page);
        model.addAttribute("order", order);
        model.addAttribute("url", "/cooperator-other-orders" + author.getId());

        return "cooperatorOtherOrders";
    }


}