package com.example.radioadsapp.controller;


import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.AdvertService;
import com.example.radioadsapp.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    final PaymentServiceImpl paymentService;
    private final ClientServiceImpl clientService;

    private final PaymentTypeServiceImpl paymentTypeService;
    private final AdvertServiceImpl advertService;
    private final RadioStationServiceImpl radioStationService;
    private final NotificationRepository notificationRepository;

    public PaymentController(PaymentServiceImpl paymentService, ClientServiceImpl clientService, PaymentTypeServiceImpl paymentTypeService, AdvertServiceImpl advertService, RadioStationServiceImpl radioStationService, NotificationRepository notificationRepository) {
        this.paymentService = paymentService;
        this.clientService = clientService;
        this.paymentTypeService = paymentTypeService;
        this.advertService = advertService;
        this.radioStationService = radioStationService;
        this.notificationRepository = notificationRepository;
    }


    @GetMapping("/list")
    public String getPayments(Model model, HttpServletRequest request) {
        model.addAttribute("payments", paymentService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/payment/list";
    }


    @GetMapping("add")
    public String addPage(@RequestParam(required = false) Long advertId, Model model, HttpServletRequest request) {

        Payment payment = new Payment();
        List<Advert> adverts = advertService.getAll();
        if(advertId != null){
            adverts.stream().filter(advert -> advertId.equals(advert.getId())).findAny().ifPresent(advert1 -> {
                payment.setAdvert(advert1);
                if(advert1.getRadioStation() != null){
                    payment.setRadioStation(advert1.getRadioStation());
                    
                }
                if(advert1.getClient() != null){
                    payment.setClient(advert1.getClient());
                }
            });
        }

        model.addAttribute("payment", payment);
        model.addAttribute("paymentType", paymentTypeService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("adverts", adverts);
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);

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
