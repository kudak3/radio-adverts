package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Advertiser;
import com.example.radioadsapp.service.impl.AdvertiserServiceImpl;
import com.example.radioadsapp.service.impl.UserServiceImpl;
import com.example.radioadsapp.utils.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/advertisers/")
public class AdvertiserController {

    private final AdvertiserServiceImpl advertiserService;


    private final UserServiceImpl userService;

    public AdvertiserController(AdvertiserServiceImpl advertiserService, UserServiceImpl userService) {
        this.advertiserService = advertiserService;
        this.userService = userService;
    }

    @GetMapping("list")
    public String listAdvertisers(Model model) {
        model.addAttribute("advertisers", advertiserService.getAll());
        return "admin/advertiser/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {

        Advertiser advertiser = new Advertiser();
        model.addAttribute("advertiser", advertiser);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("users",userService.findAllUsers());



        return "admin/advertiser/add";
    }

    @GetMapping("view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        Advertiser advertiser = advertiserService.getAdvertiser(id);
        model.addAttribute("advertiser",advertiser);

        return "admin/advertiser/view";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute("advertiser") Advertiser advertiser) {
        System.out.println("========advertiser");
        // save user to database
        advertiserService.save(advertiser);
        return "redirect:/advertisers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdvertiser(@PathVariable(value = "id") long id) {

        // call delete advertiser payment-type
        advertiserService.delete(id);
        return "redirect:/advertisers/list";
    }
}
