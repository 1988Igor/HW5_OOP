package personal.controllers;

import personal.check.TelephoneValidator;
import personal.model.Fields;
import personal.model.Repository;
import personal.model.User;

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
        if (field == Fields.PHONE) {
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


}










