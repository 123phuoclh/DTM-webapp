package org.example.Backend.dto;

import org.example.Backend.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO tmp = new UserDTO();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setNickName(user.getNickName());
        tmp.setLogin(user.getLogin());
        tmp.setAvatar(user.getAvatar());

        return tmp;
    }
}
