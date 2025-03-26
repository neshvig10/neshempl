package com.neshempl.usermanagement.repository;

import com.neshempl.usermanagement.dto.LoginRequest;
import com.neshempl.usermanagement.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {


    User getReferenceByuserPhone(Long userPhone);

    boolean existsByuserPhone(Long userPhone);

    User getByUserPhone(Long userPhone);

    User getByUserId(Long userId);

    User getByUserEmail(String userName);


    @Modifying
    @Query("UPDATE User u SET u.userName = :userName , u.userPhone = :userPhone , u.userEmail = :userEmail , u.userPassword = :userPassword WHERE u.userId = :userId")
    void updateUserDetail(@Param(value = "userId") Long userId,
                          @Param(value = "userName") String userName,
                          @Param(value = "userPhone") Long userPhone,
                          @Param(value = "userEmail") String userEmail,
                          @Param(value = "userPassword") String userPassword
                          );

    boolean existsByuserEmail(String userEmail);
}
