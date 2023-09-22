package org.example.Backend.dto;

import org.example.Backend.model.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO tmp = new UserDTO();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setUsername(user.getUsername());
        tmp.setNickName(user.getNickName());
        tmp.setEmail(user.getEmail());
        tmp.setAvatar(user.getAvatar());

        return tmp;
    }
}
