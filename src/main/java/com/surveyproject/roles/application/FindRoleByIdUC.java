package com.surveyproject.roles.application;

import java.util.Optional;

import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.domain.service.RoleService;

public class FindRoleByIdUC {
    private final RoleService service;

    public FindRoleByIdUC(RoleService service){
        this.service = service;
    }

    public Optional<Roles> findById(long id){
        return service.findRoleById(id);
    }
}
