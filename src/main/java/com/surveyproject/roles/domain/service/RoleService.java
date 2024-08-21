package com.surveyproject.roles.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.roles.domain.entity.Roles;

public interface RoleService {
    void createRole(Roles role);
    Optional<Roles> findRoleById(long id);
    List<Roles> listAllRoles();
    void updateRole(Roles role);
    void deleteRole(long id);
}
