package com.surveyproject.users.application;

import com.surveyproject.users.domain.service.UserService;

public class DeleteUserUC {
    private final UserService service;

    public DeleteUserUC(UserService service){
        this.service = service;
    }

    public void delete(long id){
        service.deleteUser(id);
    }
}
