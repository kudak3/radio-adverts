package com.example.radioadsapp.controller;

import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("config/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "admin/role/list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {


        Role role = new Role();
        model.addAttribute("role", role);
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
        roleService.saveRole(role);
        return "redirect:/config/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable(value = "id") long id) {

        // call delete role
        roleService.deleteRole(id);
        return "redirect:/config/roles";
    }
}
