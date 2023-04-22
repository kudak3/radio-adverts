package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.PaymentType;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.PaymentTypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/config/payment-types/")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;
    private final NotificationRepository notificationRepository;

    public PaymentTypeController(PaymentTypeService paymentTypeService, NotificationRepository notificationRepository) {
        this.paymentTypeService = paymentTypeService;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("list")
    public String getPaymentMethods(Model model, HttpServletRequest request) {
        model.addAttribute("paymentTypes", paymentTypeService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/payment-type/list";
    }


    @GetMapping("add")
    public String addPage(Model model, HttpServletRequest request) {

        PaymentType paymentType = new PaymentType();
        model.addAttribute("paymentType", paymentType);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);

        return "admin/payment-type/add";
    }

    @PostMapping("save")
    public String savePayment(@ModelAttribute("paymentType") PaymentType payment) {
        // save payment to database
        paymentTypeService.save(payment);
        return "redirect:/payment-types/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {

        // call delete payment methods
        paymentTypeService.delete(id);
        return "redirect:/payment-types/list";
    }
}
