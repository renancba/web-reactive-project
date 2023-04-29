package tech.ada.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comprovante {

    public String id;
    private String pagador;
    private String recebedor;
    private Double valor;
    private LocalDateTime data;

    private Boolean ack_usuario;

    public Comprovante(String id, String pagador, String recebedor, Double valor, LocalDateTime data) {
        this.id = id;
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.valor = valor;
        this.data = data;
        this.ack_usuario = false;
    }
}