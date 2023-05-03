package com.example.radioadsapp.controller;


import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.model.User;
import com.example.radioadsapp.repository.ClientRepository;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.repository.RadioStationRepository;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.impl.ClientServiceImpl;
import com.example.radioadsapp.service.impl.UserServiceImpl;
import com.example.radioadsapp.utils.Gender;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;
    private final ClientRepository clientRepository;
    private final RadioStationRepository radioStationRepository;


    public IndexController(UserRepository userRepository, NotificationRepository notificationRepository,
                           ClientRepository clientRepository,
                           RadioStationRepository radioStationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.clientRepository = clientRepository;
        this.radioStationRepository = radioStationRepository;
    }


    @GetMapping
    public String viewHomePage(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(auth.getName());
        if(user != null ){
            for (GrantedAuthority authority : auth.getAuthorities()) {
                if ("ADVERTISER".equals(authority.getAuthority()) && !clientRepository.existsClientByCreatedBy(auth.getName())) {
                    Client client = new Client();
                    client.setFirstName( user.getFirstName());
                    client.setLastName(user.getLastName());
                    client.setEmail( user.getEmail());
                    model.addAttribute("client", client);
                    model.addAttribute("genderList", Gender.values());
                    model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
                    model.addAttribute("request", request);
                    return "admin/client/add";

                } else if ("RADIOSTATION".equals(authority.getAuthority()) && !radioStationRepository.existsRadioStationByCreatedBy(auth.getName())) {
                    RadioStation radioStation = new RadioStation();
                    radioStation.setName(user.getName());
                    model.addAttribute("radioStation", radioStation);
                    model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
                    model.addAttribute("request", request);
                    return "admin/radio-station/add";
                }
            }

        }
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
