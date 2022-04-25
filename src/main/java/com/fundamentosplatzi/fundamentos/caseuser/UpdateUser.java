package com.fundamentosplatzi.fundamentos.caseuser;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }


    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
