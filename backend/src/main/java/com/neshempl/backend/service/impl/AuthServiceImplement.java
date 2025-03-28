package com.neshempl.backend.service.impl;

import com.neshempl.backend.config.JwtUtil;
import com.neshempl.backend.dto.LoginRequest;
import com.neshempl.backend.dto.RegisterRequest;
import com.neshempl.backend.entity.Role;
import com.neshempl.backend.entity.User;
import com.neshempl.backend.repository.RoleRepository;
import com.neshempl.backend.repository.UserRepository;
import com.neshempl.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImplement implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtil jwtUtil;


    public User getUserById(Long user_id) {
        return userRepository.getReferenceById(user_id);
    }

    public String register(RegisterRequest registerRequest) {

        Long userPhone = registerRequest.getUserPhone();
        String userEmail = registerRequest.getUserEmail();
        User user1 = userRepository.getByUserPhone(userPhone);
        User user2 = userRepository.getByUserEmail(userEmail);

        if (user1 !=null){
            return "Phone number already exists";
        }
        else if (user2 != null){
            return "Email already exists";
        }

        Set<Role> roles = new HashSet<>();
        Role role = new Role(registerRequest.getUserRole());
        roles.add(role);
        User user = new User(registerRequest.getUserName(),registerRequest.getUserPhone(), registerRequest.getUserEmail(),registerRequest.getUserPassword(),roles);
        userRepository.save(user);
        return "Registered";

    }

    public String updateRole(String role,Long userId){
        User user = userRepository.getByUserId(userId);
        Set<Role> roles = user.getUserRole();
        Role newRole = new Role(role);
        roles.add(newRole);
        userRepository.save(user);
        return "Role updated successfully";
    }





    public String login(LoginRequest loginRequest) {

        User user1 = userRepository.getByUserPhone(loginRequest.getUserPhone());
        Set <String> roleNames = new HashSet<>();

        if (user1 != null){
            Set<Role> roles = user1.getUserRole();
            for (Role role : roles){
                roleNames.add(role.getRoleName());
            }
        }

        if (user1 == null) {
            return "Phone number doesn't exist";
        } else if (!user1.getUserPassword().equals(loginRequest.getUserPassword())) {
            return "Wrong Password";
        }else if (!roleNames.contains(loginRequest.getUserRole())){
            return "User does not exists";
        }

        return (jwtUtil.generateToken(user1));
    }


}
