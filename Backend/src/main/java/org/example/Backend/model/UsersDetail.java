package org.example.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "UsersDetail")
public class UsersDetail {
    @Id
    private Long id;
    private String name;
    private String email;
    private String username;
    private String hashed_password;

    public UsersDetail(String name, String email, String username, String hashedPassword) {
        this.name = name;
        this.email = email;
        this.hashed_password = hashedPassword;
    }
}
