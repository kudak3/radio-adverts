package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.LocalNotification;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.impl.ClientServiceImpl;
import com.example.radioadsapp.service.impl.UserServiceImpl;
import com.example.radioadsapp.utils.AdvertType;
import com.example.radioadsapp.utils.Gender;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients/")
public class ClientController {

    private final ClientServiceImpl clientService;
    private final UserServiceImpl userService;
    private final NotificationRepository notificationRepository;

    public ClientController(ClientServiceImpl clientService, UserServiceImpl userService, NotificationRepository notificationRepository) {
        this.clientService = clientService;
        this.userService = userService;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("list")
    public String listClients(Model model, HttpServletRequest request) {
        LocalNotification localNotification = new LocalNotification();
        return getList(model, request,localNotification);
    }

    @GetMapping("add")
    public String addPage(Model model, HttpServletRequest request) {
        Client client = new Client();
        return getTemplate(model, request, client);

    }

    @GetMapping("update/{id}")
    public String updatePage(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {
        Client client = clientService.getClient(id);
        return getTemplate(model, request, client);
    }

    private String getTemplate(Model model, HttpServletRequest request, Client client) {
        model.addAttribute("client", client);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);

        return "admin/client/add";
    }

    @GetMapping("view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {
        Client client = clientService.getClient(id);
        model.addAttribute("client", client);

        return "admin/client/view";
    }

    @PostMapping("save")
    public String save(@ModelAttribute("client") Client client,Model model, HttpServletRequest request) {

        // save client to database
        if (client.getId() != null) {
            clientService.save(client);
        } else {
            clientService.save(client);
        }
        LocalNotification localNotification = new LocalNotification();
        localNotification.setSuccess("Record processed successfully");
        return getList(model, request, localNotification);
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(value = "id") long id,Model model, HttpServletRequest request ) {


        // call delete advert client
        try {
            clientService.delete(id);
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
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        model.addAttribute("lNotification", localNotification);
        return "admin/client/list";
    }


}
