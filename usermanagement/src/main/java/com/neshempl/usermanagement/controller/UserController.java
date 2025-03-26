package com.neshempl.usermanagement.controller;


import com.neshempl.usermanagement.entity.User;
import com.neshempl.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RequestMapping("/usermanagement/api/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{user_id}")
    public User userDetails(@PathVariable("user_id") Long userId){
        return userService.userDetails(userId);
    }

    @PutMapping(value = "/edit/{user_id}")
    public String editDetails(@PathVariable("user_id") Long user_id, User user){
        user.setUserId(user_id);
        return userService.editUserDetails(user);
    }

    @GetMapping(value = "/useridfromjwt")
    public Long userIdFromJwt(String jwt){
        return userService.userIdFromJwt(jwt);
    }
}
