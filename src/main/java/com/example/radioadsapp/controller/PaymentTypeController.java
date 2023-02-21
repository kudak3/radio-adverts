package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.PaymentType;
import com.example.radioadsapp.service.PaymentTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment-types/")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping("list")
    public String getPaymentMethods(Model model){
        model.addAttribute("paymentTypes", paymentTypeService.getAll());
        return "admin/payment-type/list";
    }


    @GetMapping("add")
    public String addPage(Model model) {




        PaymentType paymentType = new PaymentType();

        model.addAttribute("paymentType", paymentType);


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
