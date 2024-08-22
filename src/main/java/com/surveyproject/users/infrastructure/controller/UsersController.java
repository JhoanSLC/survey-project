package com.surveyproject.users.infrastructure.controller;

import com.surveyproject.users.application.CreateUserUC;
import com.surveyproject.users.application.DeleteUserUC;
import com.surveyproject.users.application.FindUserByIdUC;
import com.surveyproject.users.application.ListAllUsersUC;
import com.surveyproject.users.application.UpdateUserUC;
import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.domain.service.UserService;
import com.surveyproject.users.infrastructure.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

public class UsersController {
    private final UserService service;
    private final CreateUserUC createUserUC;
    private final DeleteUserUC deleteUserUC;
    private final FindUserByIdUC findUserByIdUC;
    private final ListAllUsersUC listAllUsersUC;
    private final UpdateUserUC updateUserUC;

    public UsersController() {
        this.service = new UsersRepository();
        this.createUserUC = new CreateUserUC(service);
        this.deleteUserUC = new DeleteUserUC(service);
        this.findUserByIdUC = new FindUserByIdUC(service);
        this.listAllUsersUC = new ListAllUsersUC(service);
        this.updateUserUC = new UpdateUserUC(service);
    }

    public void createUser(Users user) {
        createUserUC.create(user);
    }

    public void deleteUser(long id) {
        deleteUserUC.delete(id);
    }

    public Optional<Users> findUserById(long id) {
        return findUserByIdUC.findById(id);
    }

    public List<Users> listAllUsers() {
        return listAllUsersUC.listAll();
    }

    public void updateUser(Users user, long id) {
        updateUserUC.update(user, id);
    }
}
