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
        System.out.println("user1"+registerRequest.getUserPhone());
        System.out.println("user1"+registerRequest.getUserName());
        System.out.println("user1"+registerRequest.getUserRole());
        System.out.println("user1"+registerRequest.getUserPassword());

        if (userRepository.existsByuserPhone(registerRequest.getUserPhone()) || userRepository.existsByuserEmail(registerRequest.getUserEmail())) {
            User user = userRepository.getByUserPhone(registerRequest.getUserPhone());

            Set <String> roleNames = new HashSet<>();
            Set<Role> roles = new HashSet<>();
            if (user!=null){
                roles = user.getUserRole();
                for (Role role : roles){
                    roleNames.add(role.getRoleName());
                }
            }

            User user1 = userRepository.getByUserEmail(registerRequest.getUserEmail());

            Set<String> roleNames1 = new HashSet<>();
            Set<Role> roles1 = new HashSet<>();

            if (user1!=null){
                roles1 = user1.getUserRole();
                for (Role role : roles1){
                    roleNames1.add(role.getRoleName());
                }
            }



            if (roleNames.contains(registerRequest.getUserRole())){
                return ResponseEntity.ok("Phone number already exists");
            } else if (roleNames1.contains(registerRequest.getUserRole())) {
                return ResponseEntity.ok("Email already exists");
            }
            else{
                Role role = new Role(registerRequest.getUserRole());
                if (user!=null){
                    roles.add(role);
                    userRepository.save(user);
                } else if (user1!=null) {
                    roles1.add(role);
                    userRepository.save(user1);
                }

                return ResponseEntity.ok("Registered");
            }
        }
        Set <Role> roles = new HashSet<>();
        Role role = new Role();
        if (roleRepository.existsByRoleName(registerRequest.getUserRole())){
            role = roleRepository.getRoleByRoleName(registerRequest.getUserRole());
        }
        else{
            role = new Role(registerRequest.getUserRole());
        }
        roles.add(role);
        User user = new User(registerRequest.getUserName(),registerRequest.getUserPhone(),registerRequest.getUserEmail(), registerRequest.getUserPassword(),roles);
        userRepository.save(user);
        return ResponseEntity.ok("Registered");
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

//        System.out.println(user1.getUserId());
//        System.out.println(user1.getUserEmail());
//        System.out.println(user1.getUserRole());
//        System.out.println(user1.getUserPhone());

        if (user1 == null) {
            return new ResponseEntity<>("Phone number doesn't exist", HttpStatus.BAD_REQUEST);
        } else if (!user1.getUserPassword().equals(loginRequest.getUserPassword())) {
            return new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
        }else if (!roleNames.contains(loginRequest.getUserRole().toString())){
            return new ResponseEntity<>("User does not exists",HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(jwtUtil.generateToken(user1),HttpStatus.OK);
    }
}
