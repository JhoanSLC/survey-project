package com.surveyproject.usersRoles.infrastructure.controller;

import com.surveyproject.usersRoles.application.CreateUserRoleUC;
import com.surveyproject.usersRoles.application.DeleteUsersRoleUC;
import com.surveyproject.usersRoles.application.FindUserRoleByIdUC;
import com.surveyproject.usersRoles.application.ListAllUsersRolesUC;
import com.surveyproject.usersRoles.application.UpdateUserRoleUC;
import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.domain.service.UsersRoleService;
import com.surveyproject.usersRoles.infrastructure.repository.UsersRoleRepository;

import java.util.List;
import java.util.Optional;

public class UsersRolesController {
    private final UsersRoleService service;
    private final CreateUserRoleUC createUserRoleUC;
    private final DeleteUsersRoleUC deleteUsersRoleUC;
    private final FindUserRoleByIdUC findUserRoleByIdUC;
    private final ListAllUsersRolesUC listAllUsersRolesUC;
    private final UpdateUserRoleUC updateUserRoleUC;

    public UsersRolesController() {
        this.service = new UsersRoleRepository();
        this.createUserRoleUC = new CreateUserRoleUC(service);
        this.deleteUsersRoleUC = new DeleteUsersRoleUC(service);
        this.findUserRoleByIdUC = new FindUserRoleByIdUC(service);
        this.listAllUsersRolesUC = new ListAllUsersRolesUC(service);
        this.updateUserRoleUC = new UpdateUserRoleUC(service);
    }

    public void createUserRole(UsersRoles userRole) {
        createUserRoleUC.create(userRole);
    }

    public void deleteUserRole(long roleId, long userId) {
        deleteUsersRoleUC.delete(roleId, userId);
    }

    public Optional<UsersRoles> findUserRoleById(long roleId, long userId) {
        return findUserRoleByIdUC.findById(roleId, userId);
    }

    public List<UsersRoles> listAllUserRoles() {
        return listAllUsersRolesUC.listAll();
    }

    public void updateUserRole(UsersRoles userRole, long userId, long roleId) {
        updateUserRoleUC.update(userRole, userId, roleId);
    }
}
