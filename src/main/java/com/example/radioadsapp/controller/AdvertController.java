package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.service.impl.AdvertServiceImpl;
import com.example.radioadsapp.utils.AdvertType;
import com.example.radioadsapp.utils.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("adverts")
public class AdvertController {
    private final AdvertServiceImpl advertService;

    public AdvertController(AdvertServiceImpl advertService) {
        this.advertService = advertService;
    }

    @GetMapping("list")
    public String listAdverts(Model model) {
        model.addAttribute("adverts", advertService.getAll());
        return "admin/advert/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {

        Advert advert = new Advert();
        model.addAttribute("advertTypes", AdvertType.values());
        model.addAttribute("advert", advert);
        return "admin/advert/add";
    }


    @PostMapping("save")
    public String saveUser(@ModelAttribute("advert") Advert advert) {
        System.out.println("========advert");
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
