package model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDTO(
        Integer id,
        String uuid,
        String name,
        String email,
        String profile
) {
}
