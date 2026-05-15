package controller;

import model.dto.CreateUserDto;
import model.dto.UserResponseDTO;
import model.service.UserService;
import view.APIResponseTemplate;
import view.Utils;

import java.time.LocalDate;
import java.util.UUID;

public class UserController {

    private final UserService userService;
    private final Utils utils;

    public UserController(UserService userService, Utils utils) {
        this.userService = userService;
        this.utils = utils;
    }

    public void start() {

        while (true) {

            int option = utils.menu();

            switch (option) {

                case 1 -> showAllUsers();

                case 2 -> createUser();

//                case 3 -> searchUser();

                case 4 -> deleteUser();

                case 0 -> {
                    System.out.println("ត្រូវបានចាកចេញពីប្រព័ន្ធ...");
                    return;
                }

                default -> System.out.println("ការបញ្ចូលមិនត្រឹមត្រូវ!");
            }
        }
    }

    private void showAllUsers() {

        if (userService.getAllUsers().isEmpty()) {
            System.out.println("មិនមានទិន្នទ័យអ្នកប្រើប្រាស់!");
            return;
        }

        Utils.userList(userService.getAllUsers());
    }

    public APIResponseTemplate <UserResponseDTO> createUer(CreateUserDto createUserDto) {
        return APIResponseTemplate.<UserResponseDTO>builder()
                .status(200)
                .data(userService.createUser(createUserDto))
                .timeStamp(LocalDate.now())
                .build();
    }

    private void createUser() {

        String name = utils.input("=> បញ្ចូលឈ្មោះ ");
        String email = utils.input("=> បញ្ចូលអីុម៉ែល ");
        String password = utils.input("=> បញ្ចូលពាក្យសម្ងាត់ ");

        CreateUserDto dto =
                new CreateUserDto(name, email, password);

        UserResponseDTO user =
                userService.createUser(dto);

        System.out.println("ការចុះឈ្មោះជោគជ័យ...!");
        Utils.printUserInfo(user);
    }

//    private void searchUser() {
//
//        String target =
//                utils.input("Enter name");
//
//        List<UserResponseDTO> users =
//                userService.searchUserByName(target);
//
//        if (users.isEmpty()) {
//            System.out.println("User not found!");
//            return;
//        }
//
//        Utils.userList(users);
//    }

    private void deleteUser() {

        try {

            UUID uuid = UUID.fromString(
                    utils.input("=> បញ្ចូល UUID")
            );

            int result =
                    userService.deleteUserById(uuid);

            if (result > 0) {
                System.out.println("អ្នកប្រើប្រាស់ត្រូវបានលុប!");
            }

        } catch (Exception e) {
            System.out.println("UUID មិនត្រឹមត្រូវទេ!");
        }
    }
}
