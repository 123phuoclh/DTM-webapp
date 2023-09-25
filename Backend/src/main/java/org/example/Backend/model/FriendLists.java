package org.example.Backend.model;

import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;
}
