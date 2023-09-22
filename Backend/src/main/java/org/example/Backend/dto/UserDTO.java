package org.example.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  UserDTO {
    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String username;
    private String avatar;
    private String address;
    private String phoneNumber;
}
