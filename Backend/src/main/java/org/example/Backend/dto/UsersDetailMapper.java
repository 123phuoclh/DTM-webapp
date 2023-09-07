package org.example.Backend.dto;

import org.example.Backend.model.UsersDetail;

public class UsersDetailMapper {
    public static UsersDetailDTO Mapper(UsersDetail usersDetail) {
        UsersDetailDTO tmp = new UsersDetailDTO();
        tmp.setId(usersDetail.getId());
        tmp.setName(usersDetail.getName());
        tmp.setEmail(usersDetail.getEmail());
        tmp.setUsername(usersDetail.getUsername());
        tmp.setCreate_date(usersDetail.getCreate_date());
        return tmp;
    }
}
