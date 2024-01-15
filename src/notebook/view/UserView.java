package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.model.repository.impl.UserRepository;
import notebook.util.Commands;

import java.util.Objects;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }
    public void run(){
        while (true) {
            System.out.println();
            String command = UserRepository.prompt(" 1 - Создать контакт\n 2 - Просмотреть контакт\n" +
                    " 3 - Изменить контакт\n 4 - Просмотреть весь список контактов\n 5 - Удалить контакт\n" +
                    " Enter - Выйти\n   Введите комманду: ");
            System.out.println();
            if (Objects.equals(command, "")) return;
            switch (command) {
                case "1":
                    User u = UserRepository.createUser();
                    userController.saveUser(u);
                    break;
                case "2":
                    String id = UserRepository.prompt("id номер: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "3":
                    String userId = UserRepository.prompt("Введите id номер абонента: ");
                    userController.updateUser(userId, UserRepository.createUser());
                    break;

                case "4":
                    System.out.println(userController.readAll());
                    System.out.println();
                    break;
                case "5":
                    String deleteUserId = UserRepository.prompt("Введите id номер абонента, который необходимо удалить: ");
                    userController.deleteUser(Long.valueOf(deleteUserId));
                    break;
            }
        }
    }
}
