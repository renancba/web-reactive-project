package tech.ada.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Transacao {

    @Id
    public String id;

    private String pagador;
    private String recebedor;
    private Double valor;
    private LocalDateTime data;

    public Transacao(String pagador, String recebedor, Double valor) {
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

}