package model.service.serviceimpl;

import exception.FoundUserException;
import mapper.UserMapper;
import model.User;
import model.dao.UserDao;
import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDTO;
import model.service.UserService;
import view.APIResponseTemplate;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserServiceImpl(
            UserDao userDao,
            UserMapper userMapper
    ) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public   List<APIResponseTemplate<List<UserResponseDTO>>> getAllUsers() {

        return List.of(
                userDao.findAll()
        );
    }

    @Override
    public UserResponseDTO createUser(CreateUserDto dto) {

        User user = new User();

        user.setId(userDao.findAll().data().size() + 1001);
        user.setUuid(UUID.randomUUID());
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setProfile("default.png");

        userDao.save(user);

        return userMapper.userToResponse(user);
    }

    @Override
    public UserResponseDTO getUserByUuid(UUID uuid) {

        User user =
                userDao.findByUuid(uuid);

        if (user == null) {
            throw new FoundUserException(
                    "មិនមានទិន្នទ័យអ្នកប្រើប្រាស់!"
            );
        }

        return userMapper.userToResponse(user);
    }

    @Override
    public int deleteUserById(UUID uuid) {

        userDao.delete(uuid);

        return 1;
    }

    @Override
    public UserResponseDTO updateUserById(UUID uuid, UpdateRequestDto updateRequestDto) {
        return null;
    }

//    @Override
//    public List<UserResponseDTO> searchUserByName(String name) {
//
//        return userDao.findAll()
//                .stream()
//                .filter(u ->
//                        u.name()
//                                .toLowerCase()
//                                .contains(name.toLowerCase())
//                )
//                .toList();
//    }
}
