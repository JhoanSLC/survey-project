package com.surveyproject.usersRoles.application;

import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.domain.service.UsersRoleService;

public class CreateUserRoleUC {
    private final UsersRoleService service;

    public CreateUserRoleUC(UsersRoleService service){
        this.service = service;
    }

    public void create(UsersRoles userRole){
        service.createUserRole(userRole);
    }
}
