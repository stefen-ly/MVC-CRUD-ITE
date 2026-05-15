package model.dao;

import model.User;
import model.dto.CreateUserDto;
import model.dto.UserResponseDTO;
import view.APIResponseTemplate;

import java.util.List;

public interface UserDao {

    APIResponseTemplate<List<UserResponseDTO>> findAll();

    APIResponseTemplate<UserResponseDTO> createUser(CreateUserDto createUserDto);

    void save(User user);

    APIResponseTemplate<UserResponseDTO> findByUuid(String uuid);

    User findUserEntityByUuid(String uuid);

    void delete(String uuid);

    User update(User user);

    APIResponseTemplate<UserResponseDTO> searchByName(String name);
}