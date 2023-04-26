package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.impl.AdvertServiceImpl;
import com.example.radioadsapp.service.impl.ClientServiceImpl;
import com.example.radioadsapp.service.impl.ProgramServiceImpl;
import com.example.radioadsapp.service.impl.RadioStationServiceImpl;
import com.example.radioadsapp.utils.AdvertType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("adverts")
public class AdvertController {
    private final AdvertServiceImpl advertService;
    private final RadioStationServiceImpl radioStationService;
    private final ClientServiceImpl clientService;
    private final ProgramServiceImpl programService;

    public AdvertController(AdvertServiceImpl advertService, NotificationRepository notificationRepository, RadioStationServiceImpl radioStationService, ClientServiceImpl clientService,ProgramServiceImpl programService) {
        this.advertService = advertService;
        this.notificationRepository = notificationRepository;
        this.radioStationService = radioStationService;
        this.clientService = clientService;
        this.programService = programService;
    }

    private final NotificationRepository notificationRepository;

    @GetMapping("list")
    public String listAdverts(Model model, HttpServletRequest request) {
        model.addAttribute("adverts", advertService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/advert/list";
    }

    @GetMapping("add")
    public String addPage(@RequestParam(required = false) Long stationId, Model model, HttpServletRequest request) {
        Advert advert = new Advert();
        if (stationId != null) {
            radioStationService.getRadioStations().stream().filter(rStation -> stationId.equals(rStation.getId())).findAny().ifPresent(advert::setRadioStation);
        }

        System.out.println(advert);
        model.addAttribute("advertTypes", AdvertType.values());
        model.addAttribute("advert", advert);
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("programs",programService.getAll());
        model.addAttribute("request", request);
        return "admin/advert/add";
    }


    @PostMapping("save")
    public String saveUser(@ModelAttribute("advert") Advert advert) {
        System.out.println("========advert");
        System.out.println(advert);
        // save user to database
        advertService.save(advert);
        return "redirect:/adverts/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdvert(@PathVariable(value = "id") long id) {

        // call delete advert payment-type
        advertService.delete(id);
        return "redirect:/adverts/list";
    }
}
