package com.example.radioadsapp.controller;


import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("users", userRepository.count());
        model.addAttribute("newUsers",userRepository.countUsersByNewEntryIsTrue());

        model.addAttribute("notifications",notificationRepository.countNotificationsByViewedIsFalse());
        return "index";
    }

    @GetMapping("/config")
    public String configurationPage(){
        return "admin/configurations";
    }

}
