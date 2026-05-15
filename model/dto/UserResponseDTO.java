package model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDTO(
        Integer id,
        UUID uuid,
        String name,
        String email,
        String profile
) {
}
