package tech.ada.user.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.user.model.User;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String username;
    private List<String> roles;
    private Long birth;
    private LocalDate since;

    public User update(User usuario) {
        // id, username and entrou not permited change
        usuario.setName(this.name);
        usuario.setBirth(this.birth);
        usuario.setRoles(usuario.getRoles());
        usuario.setSince(this.since);
        return usuario;
    }

    public User create() {
        User user = new User();
        user.setName(this.name);
        user.setBirth(this.birth);
        user.setSince(this.since);
        user.setUsername(this.username);
        user.setRoles(this.roles);
        return user;
    }

}