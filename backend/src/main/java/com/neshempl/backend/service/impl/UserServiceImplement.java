package com.neshempl.backend.service.impl;


import com.neshempl.backend.config.JwtUtil;
import com.neshempl.backend.entity.User;
import com.neshempl.backend.repository.UserRepository;
import com.neshempl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    public User userDetails(Long userId){
        return userRepository.getByUserId(userId);
    }

    public Long userIdFromJwt(String jwt){
        return jwtUtil.extractUserId(jwt);
    }

    public String  editUserDetails(User user){
        User user1 = userRepository.getByUserPhone(user.getUserPhone());
        User user2 = userRepository.getByUserEmail(user.getUserEmail());
        String messageTobeSent="";
        if (user1!=null){
            messageTobeSent = "Phone number already exists";
        }
        if (user2!=null){
            messageTobeSent = "Email already exists";
        }
        if (!messageTobeSent.isEmpty()){
            return messageTobeSent;
        }
        userRepository.updateUserDetail(user.getUserId(),user.getUserName(),user.getUserPhone(),user.getUserEmail(),user.getUserPassword());
        return "Updated";
    }
}
