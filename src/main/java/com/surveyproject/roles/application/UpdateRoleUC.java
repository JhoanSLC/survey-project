package com.surveyproject.roles.application;

import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.domain.service.RoleService;

public class UpdateRoleUC {
    private final RoleService service;

    public UpdateRoleUC(RoleService service){
        this.service = service;
    }

    public void update(Roles role){
        service.updateRole(role);
    }
}
