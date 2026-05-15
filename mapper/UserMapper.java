package mapper;

import model.User;
import model.dto.UserResponseDTO;

public class UserMapper {

    public UserResponseDTO userToResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .uuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .profile(user.getProfile())
                .build();
    }
}
