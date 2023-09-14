package org.example.Backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Backend.model.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> role;
    private User user;

    public JwtResponse(String token, Long id, String username, List<String> role, User user) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
        this.user = user;
    }
}
