package com.neshempl.backend.controller;

import com.neshempl.backend.dto.LoginRequest;
import com.neshempl.backend.dto.RegisterRequest;
import com.neshempl.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@RestController
public class AuthController {


    @Autowired
    AuthService authService;

    @PostMapping(value = "/login")
    private String userLogin(LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping(value = "/register")
    private String userRegistration(RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    @PostMapping(value = "/addRole")
    private String updateRole(String role,Long userId){
        return authService.updateRole(role,userId);
    }

}
