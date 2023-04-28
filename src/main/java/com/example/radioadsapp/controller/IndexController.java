package com.example.radioadsapp.controller;


import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.repository.UserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    public IndexController(UserRepository userRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }


    @GetMapping
    public String viewHomePage(Model model, HttpServletRequest request) {
        model.addAttribute("users", userRepository.count());
        model.addAttribute("newUsers",userRepository.countUsersByNewEntryIsTrue());
        model.addAttribute("notifications",notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "index";
    }

    @GetMapping("/config")
    public String configurationPage(Model model,HttpServletRequest request){

        model.addAttribute("notifications",notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/configurations";
    }

}
