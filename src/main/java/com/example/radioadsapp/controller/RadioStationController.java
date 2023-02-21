package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.service.impl.RadioStationServiceImpl;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("radio-stations")
public class RadioStationController {
    @Autowired
    private RadioStationServiceImpl radioStationService;

    @GetMapping()
    public String listRadioStations(Model model) {
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        return "admin/radio-station/list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {


        RadioStation radioStation = new RadioStation();
        model.addAttribute("radioStation", radioStation);
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
