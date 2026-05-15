package model.service;

import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDTO;
import view.APIResponseTemplate;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<APIResponseTemplate <List<UserResponseDTO>>>getAllUsers();
    UserResponseDTO createUser(CreateUserDto dto);
    UserResponseDTO getUserByUuid(UUID uuid);
    int deleteUserById(UUID uuid);
    UserResponseDTO updateUserById(UUID uuid, UpdateRequestDto updateRequestDto);
//    List<UserResponseDTO> searchUserByName(String name);
}
