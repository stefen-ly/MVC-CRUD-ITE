package model.dao;

import model.User;
import model.dto.UserResponseDTO;
import view.APIResponseTemplate;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    APIResponseTemplate<List<UserResponseDTO>> findAll();
    void save(User user);
    User findByUuid(UUID uuid);
    void delete(UUID uuid);
    User update(User user);
}
