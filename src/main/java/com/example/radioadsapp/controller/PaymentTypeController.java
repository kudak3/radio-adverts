package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.LocalNotification;
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
        LocalNotification localNotification = new LocalNotification();
        return getList(model, request,localNotification);
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
    public String savePayment(@ModelAttribute("paymentType") PaymentType payment,Model model, HttpServletRequest request) {
        // save payment to database
        paymentTypeService.save(payment);
        LocalNotification localNotification = new LocalNotification();
        localNotification.setError("Record saved successfully");
        return getList(model, request, localNotification);
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id,Model model, HttpServletRequest request) {
        // call delete payment methods
        try {
        paymentTypeService.delete(id);
        LocalNotification localNotification = new LocalNotification();
        localNotification.setSuccess("Record Deleted Successfully");
        return getList(model, request,localNotification);

    }catch (Exception e){
        LocalNotification localNotification = new LocalNotification();
        localNotification.setError("Failed to delete record");
        return getList(model, request,localNotification);

    }
}

    private String getList(Model model, HttpServletRequest request , LocalNotification localNotification) {
        model.addAttribute("paymentTypes", paymentTypeService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        model.addAttribute("lNotification", localNotification);
        return "admin/payment-type/list";

    }
}
