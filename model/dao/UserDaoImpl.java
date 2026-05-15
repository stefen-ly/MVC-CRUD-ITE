package model.dao;

import mapper.UserMapper;
import model.User;
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
                        UUID.randomUUID(),
                        "Ally Beven",
                        "ally@gmail.com",
                        "12345",
                        "ally.png"
                )
        );
    }

    @Override
    public APIResponseTemplate<List<UserResponseDTO>> findAll() {
        return APIResponseTemplate.<List<UserResponseDTO>>builder()
                .status(200)
                .message("ជោគជ័យ...!")
                .timeStamp(LocalDate.now())
                .data(users.stream()
                        .map(userMapper::userToResponse)
                        .toList())
                .build();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User findByUuid(UUID uuid) {

        return users.stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
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
    public List<User> searchByName(String name) {

        return users.stream()
                .filter(user ->
                        user.getName()
                                .toLowerCase()
                                .contains(name.toLowerCase())
                )
                .toList();
    }
}
