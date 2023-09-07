package org.example.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


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
    private Date create_date;
}
