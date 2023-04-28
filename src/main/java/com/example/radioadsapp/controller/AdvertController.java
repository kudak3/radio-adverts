package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.LocalNotification;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.impl.*;
import com.example.radioadsapp.utils.AdvertType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("adverts")
public class AdvertController {
    private final AdvertServiceImpl advertService;
    private final RadioStationServiceImpl radioStationService;
    private final ClientServiceImpl clientService;
    private final ProgramServiceImpl programService;
    private final NotificationService notificationService;

    public AdvertController(AdvertServiceImpl advertService, NotificationRepository notificationRepository, RadioStationServiceImpl radioStationService, ClientServiceImpl clientService, ProgramServiceImpl programService, NotificationService notificationService) {
        this.advertService = advertService;
        this.notificationRepository = notificationRepository;
        this.radioStationService = radioStationService;
        this.clientService = clientService;
        this.programService = programService;
        this.notificationService = notificationService;
    }

    private final NotificationRepository notificationRepository;

    @GetMapping("list")
    public String listAdverts(Model model, HttpServletRequest request) {
        LocalNotification localNotification = new LocalNotification();
        model.addAttribute("lNotification", localNotification);
        return getList(model, request,localNotification);

    }

    @GetMapping("add")
    public String addPage(@RequestParam(required = false) Long stationId, Model model, HttpServletRequest request) {
        Advert advert = new Advert();
        if (stationId != null) {
            radioStationService.getRadioStations().stream().filter(rStation -> stationId.equals(rStation.getId())).findAny().ifPresent(advert::setRadioStation);
        }
        return getTemplate(model, request, advert);
    }

    @GetMapping("update/{id}")
    public String updatePage(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {
        Advert advert = advertService.getAdvert(id);
        return getTemplate(model, request, advert);
    }

    private String getTemplate(Model model, HttpServletRequest request, Advert advert) {
        model.addAttribute("advertTypes", AdvertType.values());
        model.addAttribute("advert", advert);
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("programs", programService.getAll());
        model.addAttribute("request", request);
        return "admin/advert/add";
    }


    @GetMapping("air")
    public String airAdvertPage(@RequestParam(required = false) String title, Model model, HttpServletRequest request) {

        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);

        Advert advert = advertService.findByTitle(title);
        System.out.println(advert);
        if (advert == null) {
            model.addAttribute("radioStations", radioStationService.getRadioStations());
            model.addAttribute("shows", programService.getAll());
            model.addAttribute("adverts", advertService.getAll());
            LocalNotification localNotification = new LocalNotification();
            localNotification.setError("Something went wrong.Please contact admin");
            model.addAttribute("lNotification", localNotification);
            return "admin/schedule/schedule";
        }
        if(advert.getAdvertType().equals(AdvertType.ONAIR)){
            LocalNotification localNotification = new LocalNotification();
            localNotification.setSuccess(advert.getTitle() + " is now airing.");
            notificationService.sendNotification(advert);
            model.addAttribute("radioStations", radioStationService.getRadioStations());
            model.addAttribute("shows", programService.getAll());
            model.addAttribute("adverts", advertService.getAll());
            model.addAttribute("lNotification", localNotification);
            return "admin/schedule/schedule";
        }

        model.addAttribute("advert", advert);
        return "admin/advert/air";
    }


    @PostMapping("save")
    public String save(@RequestParam(required = false) boolean air, @ModelAttribute("advert") Advert advert,Model model, HttpServletRequest request) {
        System.out.println("==========================-=-==-=-=-=-=-=-=-=-=-=");
        System.out.println(advert);
        // save record to database
        if (advert.getId() != null) {
            if (air) advertService.postAdvert(advert);
            else advertService.update(advert);
        } else {
            advertService.save(advert);
        }
        if(air){
            notificationService.sendNotification(advert);
            LocalNotification localNotification = new LocalNotification();
            localNotification.setSuccess("Advert has been posted to successfully" );
            model.addAttribute("radioStations", radioStationService.getRadioStations());
            model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
            model.addAttribute("shows", programService.getAll());
            model.addAttribute("adverts", advertService.getAll());
            model.addAttribute("request", request);
            model.addAttribute("lNotification", localNotification);
            return "admin/schedule/schedule";
        }
        LocalNotification localNotification = new LocalNotification();
        localNotification.setSuccess("Record processed successfully");
        return getList(model, request, localNotification);
    }

    @GetMapping("/delete/{id}")
    public String deleteAdvert(@PathVariable(value = "id") long id,Model model, HttpServletRequest request) {

        // call delete advert payment
        try {
            advertService.delete(id);
            LocalNotification localNotification = new LocalNotification();
            localNotification.setSuccess("Record Deleted Successfully");
            return getList(model, request,localNotification);

        }catch (Exception e){
            LocalNotification localNotification = new LocalNotification();
            localNotification.setError("Cannot delete record as it is used somewhere else");
            return getList(model, request,localNotification);

        }

    }

    private String getList(Model model, HttpServletRequest request , LocalNotification localNotification) {
        model.addAttribute("adverts", advertService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        model.addAttribute("lNotification", localNotification);
        return "admin/advert/list";
    }
}
