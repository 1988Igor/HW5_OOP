package personal.views;

import personal.check.PhoneException;
import personal.check.TelephoneValidator;
import personal.controllers.UserController;
import personal.model.Fields;
import personal.model.Repository;
import personal.model.User;


import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private final UserController userController;
    private final TelephoneValidator validator;
    private final Repository repository;

    public ViewUser(UserController userController, TelephoneValidator validator, Repository repository) {
        this.userController = userController;
        this.validator = validator;
        this.repository = repository;
    }

    public void run() {
        Commands com = Commands.NONE;
        showHelp();
        while (true) {
            try {
                String command = prompt("Enter a command: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        create();
                        break;
                    case READ:
                        read();
                        break;
                    case UPDATE:
                        update();
                        break;
                    case LIST:
                        list();
                        break;
                    case DELETE:
                        delete();
                        break;
                    case HELP:
                        showHelp();
                }
            } catch (Exception ex) {
                System.out.println("An error has occurred " + ex.toString());
            }
        }
    }

    private void read() throws Exception {
        String id = prompt("User ID: ");
        User user_ = userController.readUser(id);
        System.out.println(user_);
    }

    private void update() throws Exception {
        String userid = prompt("User ID: ");
        String field_name = prompt("Which field (NAME,LASTNAME,PHONE): ");
        String param = null;
        if (Fields.valueOf(field_name) == Fields.PHONE) {
            param = catchTelephone(param);
            if (param == null) {
                return;
            }
        } else {
            param = prompt("Enter what you want to change: ");
        }
        User _user = userController.readUser(userid);
        userController.updateUser(_user, Fields.valueOf(field_name.toUpperCase()), param);
    }

    public String catchTelephone(String telephone) throws Exception {
        while (true) {
            try {
                telephone = prompt("Enter the phone number (for cancel type 0): ");
                if (telephone.equals("0")) {
                    System.out.println("You declined to enter to change the user");
                    return null;
                }
                validator.checkNumber(telephone);
                return telephone;
            } catch (PhoneException ex) {
                System.out.println("An error has occurred " + ex.toString());
            }
        }
    }

    private void list() throws Exception {
        for (User user : userController.getUsers()) {
            System.out.println(user);
        }
    }

    private void create() throws Exception {
        String firstName = prompt("First Name: ");
        String lastName = prompt("Last Name: ");
        String phone = null;
        phone = catchTelephone(phone);
        if (phone == null) {
            return;
        }

        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void showHelp() {
        System.out.println("List of commands:");
        for (Commands c : Commands.values()) {
            System.out.println(c);
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private void delete() throws Exception {
        String userId = prompt("Enter the ID user for deleting: ");
        User user = userController.readUser(userId);
        if (user.getId().equals(userId)) {
            repository.deleteUser(userId);
        }

    }
}