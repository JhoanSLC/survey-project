package com.surveyproject.roles.application;

import java.util.List;

import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.domain.service.RoleService;

public class ListAllRolesUC {
    private final RoleService service;

    public ListAllRolesUC(RoleService service){
        this.service = service;
    }

    public List<Roles> listAll(){
        return service.listAllRoles();
    }
}
