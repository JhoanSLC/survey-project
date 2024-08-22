package com.surveyproject.roles.infrastructure.controller;

import com.surveyproject.roles.application.CreateRoleUC;
import com.surveyproject.roles.application.DeleteRoleUC;
import com.surveyproject.roles.application.FindRoleByIdUC;
import com.surveyproject.roles.application.ListAllRolesUC;
import com.surveyproject.roles.application.UpdateRoleUC;
import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.domain.service.RoleService;
import com.surveyproject.roles.infrastructure.repository.RolesRepository;

import java.util.List;
import java.util.Optional;

public class RolesController {
    private final RoleService service;
    private final CreateRoleUC createRoleUC;
    private final DeleteRoleUC deleteRoleUC;
    private final FindRoleByIdUC findRoleByIdUC;
    private final ListAllRolesUC listAllRolesUC;
    private final UpdateRoleUC updateRoleUC;

    public RolesController() {
        this.service = new RolesRepository();
        this.createRoleUC = new CreateRoleUC(service);
        this.deleteRoleUC = new DeleteRoleUC(service);
        this.findRoleByIdUC = new FindRoleByIdUC(service);
        this.listAllRolesUC = new ListAllRolesUC(service);
        this.updateRoleUC = new UpdateRoleUC(service);
    }

    public void createRole(Roles role) {
        createRoleUC.create(role);
    }

    public void deleteRole(long id) {
        deleteRoleUC.delete(id);
    }

    public Optional<Roles> findRoleById(long id) {
        return findRoleByIdUC.findById(id);
    }

    public List<Roles> listAllRoles() {
        return listAllRolesUC.listAll();
    }

    public void updateRole(Roles role, long id) {
        updateRoleUC.update(role, id);
    }
}
