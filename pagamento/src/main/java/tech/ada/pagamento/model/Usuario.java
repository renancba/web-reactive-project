package tech.ada.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private String name;
    private String username;
    private List<String> roles;
    private Long birth;
    private LocalDate since;
    private Double balance;

}