package org.example.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "friend_lists")
public class FriendLists {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String nick_name;

    @Column(unique = true)
    private String email;

    private String address;

    private String phoneNumber;
}
