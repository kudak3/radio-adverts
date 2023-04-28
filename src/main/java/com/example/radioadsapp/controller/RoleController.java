package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("config/roles")
public class RoleController {
    private final RoleServiceImpl roleService;
    private final NotificationRepository notificationRepository;

    public RoleController(RoleServiceImpl roleService, NotificationRepository notificationRepository) {
        this.roleService = roleService;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping()
    public String listRoles(Model model, HttpServletRequest request) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/role/list";
    }

    @GetMapping("/add")
    public String addPage(Model model, HttpServletRequest request) {


        Role role = new Role();
        model.addAttribute("role", role);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/role/add";
    }

    @GetMapping("/{id}")
    public String viewPage(@PathVariable Long id, Model model) {

        Role role = roleService.getRole(id);
        model.addAttribute("role",role);

        return "admin/role/view";
    }

    @PostMapping()
    public String saveRole(@ModelAttribute("role") Role role) {
        // save role to database
        roleService.save(role);
        return "redirect:/config/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable(value = "id") long id) {

        // call delete role
        roleService.delete(id);
        return "redirect:/config/roles";
    }
}
