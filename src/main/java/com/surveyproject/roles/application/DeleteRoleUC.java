package com.surveyproject.roles.application;

import com.surveyproject.roles.domain.service.RoleService;

public class DeleteRoleUC {
    private final RoleService service;

    public DeleteRoleUC(RoleService service){
        this.service = service;
    }

    public void delete(long id){
        service.deleteRole(id);
    }
}
