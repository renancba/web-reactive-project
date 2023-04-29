package tech.ada.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    private String name;
    public String id;
    private String pagador;
    private String recebedor;
    private Double valor;
    private LocalDateTime data;
    private Boolean ack_usuario;

    public Test(String abc) {
        this.name = abc;
    }

}
