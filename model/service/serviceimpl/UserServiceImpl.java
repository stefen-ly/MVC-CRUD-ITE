package model.service.serviceimpl;

import mapper.UserMapper;
import model.User;
import model.dao.UserDao;
import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDTO;
import model.service.UserService;
import view.APIResponseTemplate;

import java.time.LocalDate;
import java.util.List;

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
    public APIResponseTemplate<List<UserResponseDTO>> getAllUsers() {

        return userDao.findAll();
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> createUser(
            CreateUserDto createUserDto
    ) {

        return userDao.createUser(createUserDto);
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> getUserByUuid(
            String uuid
    ) {

        return userDao.findByUuid(uuid);
    }

    @Override
    public int deleteUserByUuid(String uuid) {

        User user =
                userDao.findUserEntityByUuid(uuid);

        if (user != null) {

            userDao.delete(uuid);

            return 1;
        }

        return 0;
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> updateUserByUuid(
            String uuid,
            UpdateRequestDto updateRequestDto
    ) {

        User oldUser =
                userDao.findUserEntityByUuid(uuid);

        if (!updateRequestDto.name().isBlank()) {
            oldUser.setName(updateRequestDto.name());
        }

        if (!updateRequestDto.email().isBlank()) {
            oldUser.setEmail(updateRequestDto.email());
        }

        if (!updateRequestDto.password().isBlank()) {
            oldUser.setPassword(updateRequestDto.password());
        }

        if (!updateRequestDto.profile().isBlank()) {
            oldUser.setProfile(updateRequestDto.profile());
        }

        User updatedUser =
                userDao.update(oldUser);

        return APIResponseTemplate
                .<UserResponseDTO>builder()
                .status(200)
                .message("ធ្វើបច្ចុប្បន្នភាពជោគជ័យ!")
                .timeStamp(LocalDate.now())
                .data(
                        userMapper.userToResponse(updatedUser)
                )
                .build();
    }

    @Override
    public APIResponseTemplate<UserResponseDTO> searchUserByName(
            String name
    ) {

        return userDao.searchByName(name);
    }
}