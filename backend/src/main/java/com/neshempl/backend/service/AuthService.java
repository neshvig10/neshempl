package com.neshempl.backend.service;


import com.neshempl.backend.dto.LoginRequest;
import com.neshempl.backend.dto.RegisterRequest;
import com.neshempl.backend.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    User getUserById(Long user_id);

    public String register(RegisterRequest registerRequest);

    public String login(LoginRequest loginRequest);

    public String updateRole(String role,Long userId);
}



