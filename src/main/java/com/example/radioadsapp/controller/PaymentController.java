package com.example.radioadsapp.controller;


import com.example.radioadsapp.model.Advertiser;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.service.impl.AdvertiserServiceImpl;
import com.example.radioadsapp.service.impl.PaymentServiceImpl;
import com.example.radioadsapp.service.impl.PaymentTypeServiceImpl;
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

    public PaymentController(PaymentServiceImpl paymentService, AdvertiserServiceImpl advertiserService) {
        this.paymentService = paymentService;
        this.advertiserService = advertiserService;
    }

    private final AdvertiserServiceImpl advertiserService;

    @Autowired
    private PaymentTypeServiceImpl paymentTypeService;



    @GetMapping("/list")
    public String getPayments(Model model){
        model.addAttribute("payments", paymentService.getAll());
        return "admin/payment/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {
        List<Advertiser> advertisers = advertiserService.getAll();



        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("paymentType", paymentTypeService.getAll());
        model.addAttribute("advertisers",advertisers);

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
