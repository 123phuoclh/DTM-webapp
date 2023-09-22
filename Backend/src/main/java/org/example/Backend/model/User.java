package org.example.Backend.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String nickName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String avatar;

    private String address;

    private String phoneNumber;
}
