package com.surveyproject.usersRoles.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.usersRoles.domain.entity.UsersRoles;

public interface UsersRoleService {
    void createUserRole(UsersRoles userRole);
    Optional<UsersRoles> findUserRoleById(long roleId, long userId);
    List<UsersRoles> listAllUserRoles();
    void updateUserRole(UsersRoles userRole);
    void deleteUserrole(long roleId, long userId);
}
