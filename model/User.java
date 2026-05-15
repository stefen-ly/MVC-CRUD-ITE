package model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    private String profile;
}
