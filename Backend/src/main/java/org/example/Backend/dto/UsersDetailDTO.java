package org.example.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersDetailDTO {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
}
