package controller;

import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDTO;
import model.service.UserService;
import view.APIResponseTemplate;
import view.Utils;

import java.util.List;


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

        APIResponseTemplate<List<UserResponseDTO>> users =
                userService.getAllUsers();

        if (users.data().isEmpty()) {

            System.out.println(
                    "មិនមានទិន្នន័យអ្នកប្រើប្រាស់!"
            );

            return;
        }

        Utils.userList(users);
    }

    private void createUser() {

        String name =
                utils.requiredInput(
                        "=> បញ្ចូលឈ្មោះ",
                        "ឈ្មោះមិនអាចទទេបានទេ!"
                );

        String email =
                utils.requiredInput(
                        "=> បញ្ចូលអ៊ីម៉ែល",
                        "អ៊ីម៉ែលមិនអាចទទេបានទេ!"
                );

        String password =
                utils.requiredInput(
                        "=> បញ្ចូលពាក្យសម្ងាត់",
                        "ពាក្យសម្ងាត់មិនអាចទទេបានទេ!"
                );

        CreateUserDto dto =
                new CreateUserDto(
                        name,
                        email,
                        password
                );

        APIResponseTemplate<UserResponseDTO> response =
                userService.createUser(dto);

        System.out.println("""
            
            =====[ បង្កើតអ្នកប្រើប្រាស់ជោគជ័យ ]=====
            """);

        Utils.printUserInfo(response);
    }

    private void searchUser() {

        String target =
                utils.input("=>  បញ្ចូលឈ្មោះ ");

        APIResponseTemplate<UserResponseDTO> user =
                userService.searchUserByName(target);
                Utils.printUserInfo(user);
    }

    private void deleteUser() {

        try {

            String uuid = (
                    utils.input("=> បញ្ចូល UUID ")
            );

            int result = userService.deleteUserByUuid(uuid);
            if (result > 0) {
                System.out.println("អ្នកប្រើប្រាស់ត្រូវបានលុប!");
            }

        } catch (Exception e) {
            System.out.println("UUID មិនត្រឹមត្រូវទេ!");
        }
    }

    private void updateUser() {

        try {

            String uuid = utils.input("=> បញ្ចូល UUID ");

           APIResponseTemplate<UserResponseDTO> oldUser =
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

            APIResponseTemplate <UserResponseDTO> updatedUser =
                    userService.updateUserByUuid(
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
