package tech.ada.pagamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    private String pagador;
    private String recebedor;
    private Double valor;

    public String getParamUsuarios() {
        return this.pagador + "," + this.recebedor;
    }

}