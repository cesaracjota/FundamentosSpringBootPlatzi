package com.fundamentosplatzi.fundamentos.caseuser;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }


    public User save(User newUser) {
        return userService.save(newUser);
    }
}
