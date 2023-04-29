package com.example.mercado.service;

import com.example.mercado.exceptions.ObjectNotFoundException;
import com.example.mercado.model.Mercado;
import com.example.mercado.model.Moeda;
import com.example.mercado.repository.MercadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MercadoService {
    private MercadoRepository repository;

    public MercadoService(MercadoRepository mercadoRepository) {
        this.repository = mercadoRepository;
    }
    public Mono<Mercado> salvar(Mercado mercado) {
        return repository.save(mercado);
    }
    public Mono<Mercado> atualizar(Mercado mercado, String id) {
        return repository.findById(id)
                .flatMap(atual -> repository.save(atual.update(mercado)));
    }
    public Mono<?> remover(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Mercado not found id" + id)))
                .flatMap(u -> repository.deleteById(id))
                .then();
    }
    public Mono<?> buscarPorId(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Mercado not found id" + id)))
                .then();
    }
    public Flux<Mercado> listarTodos() {
        return repository.findAll();
    }
    public Mono<?> buscarPorNome(String nomeMercado) {
        return repository.findMercadosByNome(nomeMercado)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Mercado not found name" + nomeMercado)))
                .then();
    }

    public Mono<Moeda> buscarValorMoeda(String moeda) {
        WebClient webClient = WebClient.create("https://economia.awesomeapi.com.br/");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(moeda.toUpperCase())
                        .build())
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Moeda[].class)
                                .map(moedaDTOs -> moedaDTOs[0]);
                    } else {
                        return Mono.error(new RuntimeException("Failed to get moeda"));
                    }
                });
    }

}
