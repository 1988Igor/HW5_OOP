package personal.controllers;

import personal.check.TelephoneValidator;
import personal.model.Fields;
import personal.model.Repository;
import personal.model.User;


import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final Repository repository;
    private final TelephoneValidator validator;
    public UserController(Repository repository, TelephoneValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public void saveUser(User user) throws Exception {
        validator.checkNumber(user.getPhone());
        repository.CreateUser(user);
    }
    public void updateUser(User user, Fields field, String param) throws Exception {
        if(field == Fields.TELEPHONE) {
            validator.checkNumber(param);
        }
        repository.UpdateUser(user, field, param);

    }
    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> getUsers() throws Exception {
        return repository.getAllUsers();
    }

    public void deleteUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (userId.equals(users.get(i).getId())) {
                users.remove(i);
                return;
            }
        }
        throw new Exception("User not found");
    }

}