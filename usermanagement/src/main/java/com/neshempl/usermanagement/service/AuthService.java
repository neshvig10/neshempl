package com.neshempl.usermanagement.service;


import com.neshempl.usermanagement.dto.LoginRequest;
import com.neshempl.usermanagement.dto.RegisterRequest;
import com.neshempl.usermanagement.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    User getUserById(Long user_id);

    public String register(RegisterRequest registerRequest);

    public String login(LoginRequest loginRequest);

    public String updateRole(String role,Long userId);
}



