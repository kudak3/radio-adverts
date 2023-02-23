package com.example.radioadsapp.controller;


import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.impl.RoleServiceImpl;
import com.example.radioadsapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users/")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("list")
    public String listUsers(Model model) {
        userRepository.updateAllUsers();
        model.addAttribute("users", userService.getAll());
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
