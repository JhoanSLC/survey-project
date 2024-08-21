package com.surveyproject.roles.application;

import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.domain.service.RoleService;

public class CreateRoleUC {
    private final RoleService service;

    public CreateRoleUC(RoleService service){
        this.service = service;
    }

    public void create(Roles role){
        service.createRole(role);
    }
}
