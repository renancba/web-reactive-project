package com.example.mercado.model;

import com.example.mercado.repository.MercadoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Mercado {
    @Id
    public String id;

    private String nome;
    private String moeda;
    private Double saldo;

    public Mercado update(Mercado mercado){
        this.setNome(mercado.nome);
        this.setMoeda(mercado.moeda);
        this.setSaldo(mercado.saldo);
        return this;
    }

}