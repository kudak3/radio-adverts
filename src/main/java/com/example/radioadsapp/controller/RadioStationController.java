package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.LocalNotification;
import com.example.radioadsapp.model.RadioStation;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.ProgramService;
import com.example.radioadsapp.service.impl.RadioStationServiceImpl;
import com.example.radioadsapp.utils.FileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("radio-stations")
public class RadioStationController {
    private final RadioStationServiceImpl radioStationService;

    private final NotificationRepository notificationRepository;
    private final ProgramService programService;

    public RadioStationController(RadioStationServiceImpl radioStationService, NotificationRepository notificationRepository, ProgramService programService) {
        this.radioStationService = radioStationService;
        this.notificationRepository = notificationRepository;
        this.programService = programService;
    }

    @GetMapping()
    public String listRadioStations(Model model, HttpServletRequest request) {
        LocalNotification localNotification = new LocalNotification();
        return getList(model, request, localNotification);
    }

    @GetMapping("add")
    public String addPage(Model model, HttpServletRequest request) {


        RadioStation radioStation = new RadioStation();
        model.addAttribute("radioStation", radioStation);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/radio-station/add";
    }

    @GetMapping("update/{id}")
    public String updatePage(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {
        RadioStation radioStation = radioStationService.getRadioStation(id);
        model.addAttribute("radioStation", radioStation);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/radio-station/add";
    }


    @GetMapping("/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        RadioStation radioStation = radioStationService.getRadioStation(id);
        model.addAttribute("radioStation", radioStation);

        return "schedule";
    }

    @PostMapping()
    public String saveRadioStation(@ModelAttribute("radioStation") RadioStation radioStation, @RequestParam("image") MultipartFile multipartFile, Model model, HttpServletRequest request) {
        // save/update radioStation to database
        System.out.println("#############################################");
        System.out.println(radioStation);
        LocalNotification localNotification = new LocalNotification();
        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            radioStation.setImageName(fileName);
            String uploadDir = "src/main/resources/static/img/";


            if (radioStation.getId() != null) {
               radioStation = radioStationService.update(radioStation);

            } else {
              radioStation =  radioStationService.save(radioStation);
            }
            uploadDir = uploadDir.concat(radioStation.getId().toString());
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            localNotification.setSuccess("Record saved successfully");
            return getList(model, request, localNotification);
        } catch (Exception e) {
            localNotification.setSuccess("Failed to create record");
            return getList(model, request, localNotification);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRadioStation(@PathVariable(value = "id") long id, Model model, HttpServletRequest request) {

        // call delete radioStation

        try {
            radioStationService.delete(id);
            LocalNotification localNotification = new LocalNotification();
            localNotification.setSuccess("Record Deleted Successfully");
            return getList(model, request, localNotification);

        } catch (Exception e) {
            LocalNotification localNotification = new LocalNotification();
            localNotification.setError("Failed to delete record");
            return getList(model, request, localNotification);

        }
    }

    private String getList(Model model, HttpServletRequest request, LocalNotification localNotification) {
        model.addAttribute("radioStations", radioStationService.getRadioStations());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        model.addAttribute("lNotification", localNotification);
        return "admin/radio-station/list";
    }

}
