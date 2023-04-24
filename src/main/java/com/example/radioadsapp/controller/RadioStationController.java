package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.impl.RadioStationServiceImpl;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("radio-stations")
public class RadioStationController {
    private final RadioStationServiceImpl radioStationService;

    private final NotificationRepository notificationRepository;

    public RadioStationController(RadioStationServiceImpl radioStationService, NotificationRepository notificationRepository) {
        this.radioStationService = radioStationService;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping()
    public String listRadioStations(Model model, HttpServletRequest request) {
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/radio-station/list";
    }

    @GetMapping("/add")
    public String addPage(Model model,HttpServletRequest request) {


        RadioStation radioStation = new RadioStation();
        model.addAttribute("radioStation", radioStation);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/radio-station/add";
    }

    @GetMapping("/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        RadioStation radioStation = radioStationService.getRadioStation(id);
        model.addAttribute("radioStation",radioStation);

        return "admin/radio-station/view";
    }

    @PostMapping()
    public String saveRadioStation(@ModelAttribute("radioStation") RadioStation radioStation) {
        // save radioStation to database
        radioStationService.save(radioStation);
        return "redirect:/radio-stations";
    }

    @GetMapping("/delete/{id}")
    public String deleteRadioStation(@PathVariable(value = "id") long id) {

        // call delete radioStation
        radioStationService.delete(id);
        return "redirect:/radio-stations";
    }
}
