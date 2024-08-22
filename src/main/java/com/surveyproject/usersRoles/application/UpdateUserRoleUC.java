package com.surveyproject.usersRoles.application;

import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.domain.service.UsersRoleService;

public class UpdateUserRoleUC {
    private final UsersRoleService service;

    public UpdateUserRoleUC(UsersRoleService service){
        this.service = service;
    }

    public void update(UsersRoles userRole,long userId,long roledId){
        service.updateUserRole(userRole, userId, roledId);
    }
}
