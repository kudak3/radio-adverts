package com.example.radioadsapp.controller;


import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.model.User;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.RoleService;
import com.example.radioadsapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/users/")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {

        UserDto userDto = new UserDto();
        List<Role> roles = roleService.getRoles();
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

    @GetMapping("/users")
    public String listUsers(Model model) {
        System.out.println("==================");
        model.addAttribute("users", userService.findAllUsers());
        return "admin/user/list";
    }

    @GetMapping("add")
    public String addPage(Model model) {


        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("roles",roleService.getRoles());




        return "admin/user/add";
    }

    @PostMapping("save")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto)  {


        if(!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "redirect:/users/add?error";
        // save user to database


//        String  photoName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            userDto.setPhoto(photoName);
//        User user = userService.save(userDto);
//       if(user == null)
//           return "redirect:/users/add?email";
//
//       String uploadDir = "./src/main/resources/static/profile-photos/" + user.getId();
//        Path uploadPath = Paths.get(uploadDir);
//       if(!Files.exists(uploadPath)){
//           Files.createDirectories(uploadPath);
//       }
//
//
//       try (InputStream inputStream = multipartFile.getInputStream()){
//           Path filePath = uploadPath.resolve(photoName);
//           Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
//       } catch (IOException e){
//           throw  new IOException("Could not save uploaded file" + photoName);
//       }
       return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {

        // call delete user
        userService.deleteUser(id);
        return "redirect:/users/list";
    }


}
