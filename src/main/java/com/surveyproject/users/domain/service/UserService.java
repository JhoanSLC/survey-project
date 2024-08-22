package com.surveyproject.users.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.users.domain.entity.Users;

public interface UserService {
    void createUsert(Users user);
    Optional<Users> findUserById(long id);
    List<Users> listAllUser();
    void updateUser(Users user,long id);
    void deleteUser(long id);
}
