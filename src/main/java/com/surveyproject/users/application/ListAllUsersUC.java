package com.surveyproject.users.application;

import java.util.List;

import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.domain.service.UserService;

public class ListAllUsersUC {
    private final UserService service;

    public ListAllUsersUC(UserService service){
        this.service = service;
    }

    public List<Users> listAll(){
        return service.listAllUser();
    }
}
