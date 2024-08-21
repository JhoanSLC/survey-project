package com.surveyproject.usersRoles.application;

import com.surveyproject.usersRoles.domain.service.UsersRoleService;

public class DeleteUsersRoleUC {
    private final UsersRoleService service;

    public DeleteUsersRoleUC(UsersRoleService service){
        this.service = service;
    }

    public void delete(long roleId, long userId){
        service.deleteUserrole(roleId, userId);
    }
}
