package com.neshempl.usermanagement.controller;

import com.neshempl.usermanagement.config.JwtUtil;
import com.neshempl.usermanagement.dto.LoginRequest;
import com.neshempl.usermanagement.dto.RegisterRequest;
import com.neshempl.usermanagement.entity.Role;
import com.neshempl.usermanagement.entity.User;
import com.neshempl.usermanagement.repository.UserRepository;
import com.neshempl.usermanagement.service.AuthService;
import com.neshempl.usermanagement.service.UserService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/auth")
@RestController
public class AuthController {


    @Autowired
    AuthService authService;

    @PostMapping(value = "/login")
    private ResponseEntity<String> userLogin(LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping(value = "/register")
    private ResponseEntity<String> userRegistration(RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

}
