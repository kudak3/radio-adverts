package com.example.radioadsapp.controller;


import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.UserService;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import com.example.radioadsapp.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthRegistrationController {

    private UserService userService;

    public AuthRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(@RequestParam(required = false) boolean admin, Model model) {

        UserDto userDto = new UserDto();
        List<Role> roles;
        if (admin) {
            roles = roleService.getRoles();
        } else {
            roles = roleService.getRoles().stream().filter(role -> !role.getName().equals("ADMIN")).collect(Collectors.toList());
        }

        model.addAttribute("roles", roles);
        model.addAttribute("userDto", userDto);

        return "registration";
    }


    @PostMapping("/register/save")
    public String registerUserAccount(@ModelAttribute("userDto") UserDto userDto) {

        if (!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "redirect:/register?error=Passwords do not match";
        if(Util.isValidEmail(userDto.getEmail()))
            return "redirect:/register?error=Invalid email address";
        // save user to database
        userService.saveUser(userDto);
        return "redirect:/login?success";


    }


}
