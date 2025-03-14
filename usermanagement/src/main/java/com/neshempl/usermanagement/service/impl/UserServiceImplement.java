package com.neshempl.usermanagement.service.impl;


import com.neshempl.usermanagement.entity.User;
import com.neshempl.usermanagement.repository.UserRepository;
import com.neshempl.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserRepository userRepository;

    public User userDetails(Long userId){
        return userRepository.getByUserId(userId);
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
