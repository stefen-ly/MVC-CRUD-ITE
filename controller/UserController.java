package controller;

import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDTO;
import model.service.UserService;
import view.APIResponseTemplate;
import view.Utils;

import java.time.LocalDate;
import java.util.List;
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

                case 3 -> searchUser();

                case 4 -> updateUser();
                case 5 -> deleteUser();
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

    private void searchUser() {

        String target =
                utils.input("=>  បញ្ចូលឈ្មោះ ");

        List<UserResponseDTO> users =
                userService.searchUserByName(target);

        if (users.isEmpty()) {

            System.out.println(
                    "មិនមានទិន្នទ័យអ្នកប្រើប្រាស់!"
            );

            return;
        }

        Utils.printUserInfo(users.getFirst());
    }

    private void deleteUser() {

        try {

            UUID uuid = UUID.fromString(
                    utils.input("=> បញ្ចូល UUID ")
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

    private void updateUser() {

        try {

            UUID uuid = UUID.fromString(
                    utils.input("=> បញ្ចូល UUID ")
            );

            UserResponseDTO oldUser =
                    userService.getUserByUuid(uuid);

            System.out.println("\n=====[ព័ត៌មានអ្នកប្រើប្រាស់]======");

            Utils.printUserInfo(oldUser);

            System.out.println("""
                
                Press Enter to skip updating field
                """);

            String name =
                    utils.input("=> បញ្ចូលឈ្មោះថ្មី ");

            String email =
                    utils.input("=> បញ្ចូលអីុម៉ែលថ្មី ");

            String password =
                    utils.input("=> បញ្ចូលពាក្យសម្ងាត់ថ្មី ");

            String profile =
                    utils.input("=> បញ្ចូលប្រូហ្វាល់ថ្មី ");

            UpdateRequestDto dto =
                    new UpdateRequestDto(
                            name,
                            email,
                            password,
                            profile
                    );

            UserResponseDTO updatedUser =
                    userService.updateUserById(
                            uuid,
                            dto
                    );

            System.out.println(
                    "\nការធ្វើបច្ចុប្បន្នភាពជោគជ័យ...!"
            );

            Utils.printUserInfo(updatedUser);

        } catch (Exception e) {

            System.out.println(
                    "UUID មិនត្រឹមត្រូវ!"
            );
        }
    }
}
