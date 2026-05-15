package model.service;

import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDTO;
import view.APIResponseTemplate;

import java.util.List;

public interface UserService {

    APIResponseTemplate<List<UserResponseDTO>> getAllUsers();

    APIResponseTemplate<UserResponseDTO> createUser(
            CreateUserDto dto
    );

    APIResponseTemplate<UserResponseDTO> getUserByUuid(
            String uuid
    );

    int deleteUserByUuid(String uuid);

    APIResponseTemplate<UserResponseDTO> updateUserByUuid(
            String uuid,
            UpdateRequestDto updateRequestDto
    );

    APIResponseTemplate<UserResponseDTO> searchUserByName(
            String name
    );
}