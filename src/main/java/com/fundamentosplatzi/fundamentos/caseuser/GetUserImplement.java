package com.fundamentosplatzi.fundamentos.caseuser;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
