package com.neshempl.usermanagement.service;


import com.neshempl.usermanagement.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User userDetails(Long userId);

    String  editUserDetails(User user);

    Long userIdFromJwt(String jwt);
}
