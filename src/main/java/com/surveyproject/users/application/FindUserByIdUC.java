package com.surveyproject.users.application;

import java.util.Optional;

import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.domain.service.UserService;

public class FindUserByIdUC {
    private final UserService service;

    public FindUserByIdUC(UserService service){
        this.service = service;
    }

    public Optional<Users> findById(long id){
        return service.findUserById(id)
    }
}
