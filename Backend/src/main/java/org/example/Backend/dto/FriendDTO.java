package org.example.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendDTO {
    private Long id;

    private String name;

    private String nick_name;

    private String email;

    private String address;

    private String phoneNumber;

    private Long user_id;
}
