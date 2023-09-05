package org.example.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Backend.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String nickName;
    private String login;
    private String avatar;
    public UserDTO(Optional<User> user) {
    }
}
