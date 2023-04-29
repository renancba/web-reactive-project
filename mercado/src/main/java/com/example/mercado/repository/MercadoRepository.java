package com.example.mercado.repository;

import com.example.mercado.model.Mercado;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MercadoRepository extends ReactiveMongoRepository<Mercado, String> {
    public Flux<Mercado> findMercadosByNome(String nome);
}