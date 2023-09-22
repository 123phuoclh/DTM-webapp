package org.example.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "UsersDetail")
public class UsersDetail {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String hashed_password;

    public UsersDetail(String name, String email, String username, String hashedPassword) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.hashed_password = hashedPassword;
    }
}
