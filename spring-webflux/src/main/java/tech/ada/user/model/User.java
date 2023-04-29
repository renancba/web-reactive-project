package tech.ada.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private String id;

    private String name;
    private String username;
    private List<String> roles;
    private Long birth;
    private LocalDate since;

    public User update(User usuario) {
        // id, username and since not permited changing
        this.setName(usuario.name);
        this.setBirth(usuario.birth);
        this.setRoles(usuario.getRoles());
        this.setSince(usuario.since);
        return this;
    }

}