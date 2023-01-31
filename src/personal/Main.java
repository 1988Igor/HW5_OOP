package personal;

import personal.check.TelephoneValidator;
import personal.controllers.UserController;
import personal.model.FileOperation;
import personal.model.FileOperationImpl;
import personal.model.Repository;
import personal.model.RepositoryFile;
import personal.views.ViewUser;

public class Main {

    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("MyUsers.txt");
        Repository repository = new RepositoryFile(fileOperation);
        TelephoneValidator validator = new TelephoneValidator();
        UserController controller = new UserController(repository,validator);
        ViewUser view = new ViewUser(controller, validator, repository);
        view.run();
    }
}