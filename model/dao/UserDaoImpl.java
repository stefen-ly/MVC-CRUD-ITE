
package model.dao;

import exception.FoundUserException;
import mapper.UserMapper;
import model.User;
import model.dto.CreateUserDto;
import model.dto.UserResponseDTO;
import view.APIResponseTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoImpl implements UserDao {

    private final UserMapper userMapper;

    private final List<User> users =
            new ArrayList<>();

    public UserDaoImpl(UserMapper userMapper) {

        this.userMapper = userMapper;

        users.add(
                new User(
                        1001,
                        UUID.randomUUID().toString(),
                        "Ally Beven",
                        "ally@gmail.com",
                        "12345",
                        "ally.png"
                )
        );
    }

    @Override
    public APIResponseTemplate<List<UserResponseDTO>> findAll() {

        return APIResponseTemplate
                .<List<UserResponseDTO>>builder()
                .status(200)
                .message("ជោគជ័យ...!")
                .timeStamp(LocalDate.now())
                .data(
                        users.stream()
                                .map(userMapper::userToResponse)
                                .toList()
                )
                .build();
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> createUser(
            CreateUserDto createUserDto
    ) {

        User user = new User();

        user.setId(users.size() + 1001);
        user.setUuid(UUID.randomUUID().toString());
        user.setName(createUserDto.name());
        user.setEmail(createUserDto.email());
        user.setPassword(createUserDto.password());
        user.setProfile("default.png");

        save(user);

        return APIResponseTemplate
                .<UserResponseDTO>builder()
                .status(200)
                .message("បង្កើតអ្នកប្រើប្រាស់ជោគជ័យ!")
                .timeStamp(LocalDate.now())
                .data(userMapper.userToResponse(user))
                .build();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> findByUuid(String uuid) {

        User user = users.stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(
                        () -> new FoundUserException(
                                "រកមិនឃើញអ្នកប្រើប្រាស់!"
                        )
                );

        return APIResponseTemplate
                .<UserResponseDTO>builder()
                .status(200)
                .message("ជោគជ័យ...!")
                .timeStamp(LocalDate.now())
                .data(userMapper.userToResponse(user))
                .build();
    }

    @Override
    public User findUserEntityByUuid(String uuid) {

        return users.stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(
                        () -> new FoundUserException(
                                "រកមិនឃើញអ្នកប្រើប្រាស់!"
                        )
                );
    }

    @Override
    public void delete(String uuid) {

        users.removeIf(
                u -> u.getUuid().equals(uuid)
        );
    }

    @Override
    public User update(User user) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUuid().equals(user.getUuid())) {

                users.set(i, user);

                return user;
            }
        }

        return null;
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> searchByName(String name) {

        User user = users.stream()
                .filter(
                        u -> u.getName()
                                .toLowerCase()
                                .contains(name.toLowerCase())
                )
                .findFirst()
                .orElseThrow(
                        () -> new FoundUserException(
                                "រកមិនឃើញអ្នកប្រើប្រាស់!"
                        )
                );

        return APIResponseTemplate
                .<UserResponseDTO>builder()
                .status(200)
                .message("ជោគជ័យ...!")
                .timeStamp(LocalDate.now())
                .data(userMapper.userToResponse(user))
                .build();
    }
}