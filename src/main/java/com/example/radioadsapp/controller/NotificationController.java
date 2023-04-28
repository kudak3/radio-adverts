package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.LocalNotification;
import com.example.radioadsapp.repository.NotificationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public String getNotifications(Model model, HttpServletRequest request){
        notificationRepository.updateAllNotifications();
        LocalNotification localNotification = new LocalNotification();
        return getList(model, request,localNotification);
    }

    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable(value = "id") long id,Model model, HttpServletRequest request) {

        // call delete notification
        try {
            notificationRepository.deleteById(id);
            LocalNotification localNotification = new LocalNotification();
            localNotification.setSuccess("Record Deleted Successfully");
            return getList(model, request,localNotification);

        }catch (Exception e){
            LocalNotification localNotification = new LocalNotification();
            localNotification.setError("Failed to delete record");
            return getList(model, request,localNotification);

        }
    }

    private String getList(Model model, HttpServletRequest request , LocalNotification localNotification) {
        model.addAttribute("notifications",notificationRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        model.addAttribute("notificationCount",notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        model.addAttribute("lNotification", localNotification);
        return "admin/notification/list";
    }
}
