package com.surveyproject.usersRoles.application;

import java.util.List;

import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.domain.service.UsersRoleService;

public class ListAllUsersRolesUC {
    private final UsersRoleService service;

    public ListAllUsersRolesUC(UsersRoleService service){
        this.service = service;
    }

    public List<UsersRoles> listAll(){
        return service.listAllUserRoles();
    }
}
