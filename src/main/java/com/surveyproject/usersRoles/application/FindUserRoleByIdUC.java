package com.surveyproject.usersRoles.application;

import java.util.Optional;

import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.domain.service.UsersRoleService;

public class FindUserRoleByIdUC {
    private final UsersRoleService service;

    public FindUserRoleByIdUC(UsersRoleService service){
        this.service = service;
    }

    public Optional<UsersRoles> findById(long roleId, long userId){
        return service.findUserRoleById(roleId, userId);
    }
}
