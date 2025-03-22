package com.neshempl.usermanagement.service.impl;

import com.neshempl.usermanagement.config.JwtUtil;
import com.neshempl.usermanagement.dto.LoginRequest;
import com.neshempl.usermanagement.dto.RegisterRequest;
import com.neshempl.usermanagement.entity.Role;
import com.neshempl.usermanagement.entity.User;
import com.neshempl.usermanagement.repository.RoleRepository;
import com.neshempl.usermanagement.repository.UserRepository;
import com.neshempl.usermanagement.service.AuthService;
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

    public ResponseEntity<String> register(RegisterRequest registerRequest) {

        Long userPhone = registerRequest.getUserPhone();
        String userEmail = registerRequest.getUserEmail();
        User user1 = userRepository.getByUserPhone(userPhone);
        User user2 = userRepository.getByUserEmail(userEmail);

        if (user1 !=null){
            return ResponseEntity.status(409).body("Phone number already exists");
        }
        else if (user2 != null){
            return ResponseEntity.status(409).body("Email already exists");
        }

        Set<Role> roles = new HashSet<>();
        Role role = new Role(registerRequest.getUserRole());
        roles.add(role);
        User user = new User(registerRequest.getUserName(),registerRequest.getUserPhone(), registerRequest.getUserEmail(),registerRequest.getUserPassword(),roles);
        return ResponseEntity.status(200).body("Registered !");

    }

    public ResponseEntity<String> updateRole(String role,Long userId){
        User user = userRepository.getByUserId(userId);
        Set<Role> roles = user.getUserRole();
        Role newRole = new Role(role);
        roles.add(newRole);
        userRepository.save(user);
        return ResponseEntity.status(200).body("Role updated successfully");
    }





    public ResponseEntity<String> login(LoginRequest loginRequest) {

        User user1 = userRepository.getByUserPhone(loginRequest.getUserPhone());
        Set <String> roleNames = new HashSet<>();

        if (user1 != null){
            Set<Role> roles = user1.getUserRole();
            for (Role role : roles){
                roleNames.add(role.getRoleName());
            }
        }

        if (user1 == null) {
            return new ResponseEntity<>("Phone number doesn't exist", HttpStatus.BAD_REQUEST);
        } else if (!user1.getUserPassword().equals(loginRequest.getUserPassword())) {
            return new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
        }else if (!roleNames.contains(loginRequest.getUserRole())){
            return new ResponseEntity<>("User does not exists",HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(jwtUtil.generateToken(user1),HttpStatus.OK);
    }


}
