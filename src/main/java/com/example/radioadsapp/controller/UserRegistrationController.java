package com.example.radioadsapp.controller;


import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.model.User;
import com.example.radioadsapp.repository.NotificationRepository;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import com.example.radioadsapp.service.UserService;
import com.example.radioadsapp.utils.AdvertType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users/")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService, RoleServiceImpl roleService, UserRepository userRepository, NotificationRepository notificationRepository) {
        super();
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    private final RoleServiceImpl roleService;

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;



    @GetMapping("list")
    public String listUsers(Model model, HttpServletRequest request) {
        userRepository.updateAllUsers();
        model.addAttribute("users", userService.getAll());
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/user/list";
    }


    @GetMapping("add")
    public String addPage(Model model, HttpServletRequest request) {

        UserDto user = new UserDto();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles",roles);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/user/add";
    }


    @GetMapping("update/{email}")
    public String updatePage(@PathVariable(value = "email") String email, Model model, HttpServletRequest request) {
        User user = userService.findByEmail(email);
        return getTemplate(model, request, new UserDto().convertEntityToDto(user),roleService.getRoles());
    }

    private String getTemplate(Model model, HttpServletRequest request, UserDto user,List<Role> roles) {
        model.addAttribute("user", user);
        model.addAttribute("roles",roles);
        model.addAttribute("notifications", notificationRepository.countNotificationsByViewedIsFalse());
        model.addAttribute("request", request);
        return "admin/user/add";
    }

    @PostMapping("save")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto)  {


        if(!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "redirect:/users/add?error";
        // save user to database


//        String  photoName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            userDto.setPhoto(photoName);

        userService.saveUser(userDto);
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
