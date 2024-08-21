package com.surveyproject.users.application;

import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.domain.service.UserService;

public class CreateUserUC {
    private final UserService service;

    public CreateUserUC(UserService service){
        this.service = service;
    }

    public void create(Users user){
        service.createUsert(user);
    }
}
