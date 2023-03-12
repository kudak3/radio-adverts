package com.example.radioadsapp.controller;


import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.service.AdvertService;
import com.example.radioadsapp.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    final
    PaymentServiceImpl paymentService;
    private final ClientServiceImpl clientService;

    private final PaymentTypeServiceImpl paymentTypeService;
    private final AdvertServiceImpl advertService;
    private final RadioStationServiceImpl radioStationService;

    public PaymentController(PaymentServiceImpl paymentService, ClientServiceImpl clientService, PaymentTypeServiceImpl paymentTypeService, AdvertServiceImpl advertService, RadioStationServiceImpl radioStationService) {
        this.paymentService = paymentService;
        this.clientService = clientService;
        this.paymentTypeService = paymentTypeService;
        this.advertService = advertService;
        this.radioStationService = radioStationService;
    }


    @GetMapping("/list")
    public String getPayments(Model model) {
        model.addAttribute("payments", paymentService.getAll());
        return "admin/payment/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {

        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("paymentType", paymentTypeService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("adverts", advertService.getAll());
        model.addAttribute("radioStations", radioStationService.getRadioStations());

        return "admin/payment/add";
    }

    @PostMapping("save")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        // save payment to database
        paymentService.save(payment);
        return "redirect:/payments/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {

        // call delete payment methods
        paymentService.delete(id);
        return "redirect:/payments/list";
    }
}
