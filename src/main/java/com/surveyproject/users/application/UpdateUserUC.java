package com.surveyproject.users.application;

import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.domain.service.UserService;

public class UpdateUserUC {
    private final UserService service;

    public UpdateUserUC(UserService service){
        this.service = service;
    }

    public void update(Users user, long id){
        service.updateUser(user,id);
    }
}
