package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Program;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.ProgramService;
import com.example.radioadsapp.service.impl.AdvertServiceImpl;
import com.example.radioadsapp.service.impl.RadioStationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    private final RadioStationServiceImpl radioStationService;

    private final NotificationRepository notificationRepository;
    private final ProgramService programService;
    private final AdvertServiceImpl advertService;

    public ScheduleController(RadioStationServiceImpl radioStationService, NotificationRepository notificationRepository, ProgramService programService, AdvertServiceImpl advertService) {
        this.radioStationService = radioStationService;
        this.notificationRepository = notificationRepository;
        this.programService = programService;
        this.advertService =advertService;
    }

    @GetMapping()
    public String schedulePage(Model model, HttpServletRequest request) {
        System.out.println(programService.getAll());
        System.out.println(advertService.getAll());
        System.out.println("===================================");
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("shows", programService.getAll());
        model.addAttribute("adverts", advertService.getAll());
        model.addAttribute("request", request);
        return "admin/schedule/schedule";
    }

    @GetMapping("add")
    public String showsPage( Model model, HttpServletRequest request) {

        Program show = new Program();
        model.addAttribute("show",show);
        model.addAttribute("colors", new String[]{"green", "orange", "red","blue"});
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/schedule/shows";
    }

    @PostMapping("save")
    public String saveShow(@ModelAttribute("show") Program show) {
        // save show to database
        programService.save(show);
        return "redirect:/schedule";
    }
}
