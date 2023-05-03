package com.example.radioadsapp.controller;


import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.UserService;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        if(admin){
            roles = roleService.getRoles();
        }else{
            roles = roleService.getRoles().stream().filter(role -> !role.getName().equals("ADMIN")).collect(Collectors.toList());
        }

        model.addAttribute("roles",roles);
        model.addAttribute("userDto",userDto);

        return "registration";
    }

    @PostMapping("/register/save")
    public String registerUserAccount(@ModelAttribute("userDto") UserDto userDto)  {

        if(!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "redirect:/register?password";
        // save user to database

//        String  photoName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        userDto.setPhoto(photoName);
//        System.out.println("===================userDto");
//        System.out.println(userDto);

        userService.saveUser(userDto);

//        String uploadDir = "./src/main/resources/static/profile-photos/" + user.getId();
//        Path uploadPath = Paths.get(uploadDir);
//        if(!Files.exists(uploadPath)){
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = multipartFile.getInputStream()){
//            Path filePath = uploadPath.resolve(photoName);
//            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e){
//            throw  new IOException("Could not save uploaded file" + photoName);
//        }
        return "redirect:/";


    }


}
