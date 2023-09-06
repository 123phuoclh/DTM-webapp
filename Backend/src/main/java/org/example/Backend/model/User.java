package org.example.Backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {
    @Id
    private Long id;
    private String name;
    private String nickName;
    private String login;
    private String hashed_password;
    private String avatar;
}
