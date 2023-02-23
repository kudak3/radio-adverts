package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.service.impl.ClientServiceImpl;
import com.example.radioadsapp.service.impl.UserServiceImpl;
import com.example.radioadsapp.utils.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients/")
public class ClientController {

    private final ClientServiceImpl clientService;


    private final UserServiceImpl userService;

    public ClientController(ClientServiceImpl clientService, UserServiceImpl userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping("list")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "admin/client/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {

        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("users",userService.getAll());



        return "admin/client/add";
    }

    @GetMapping("view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        Client client = clientService.getClient(id);
        model.addAttribute("client",client);

        return "admin/client/view";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute("client") Client client) {
        System.out.println("========client");
        // save user to database
        clientService.save(client);
        return "redirect:/clients/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(value = "id") long id) {

        // call delete client payment-type
        clientService.delete(id);
        return "redirect:/clients/list";
    }
}
