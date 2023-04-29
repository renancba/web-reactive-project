package com.example.mercado.controller;

import com.example.mercado.exceptions.BadRequestException;
import com.example.mercado.exceptions.ObjectNotFoundException;
import com.example.mercado.model.Mercado;
import com.example.mercado.model.Moeda;
import com.example.mercado.service.MercadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mercados")
@Slf4j
public class MercadoController {

    private MercadoService service;

    public MercadoController(MercadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Mercado>> salvar(
            @RequestBody Mercado mercado) {
        return service.salvar(mercado)
                .map(atual -> ResponseEntity.ok().body(mercado));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Mercado>> atualizar(
            @RequestBody Mercado mercado,
            @PathVariable(value = "id") String id) {
        return service.atualizar(mercado, id)
                .map(atual -> ResponseEntity.ok().body(mercado));
    }
    @GetMapping("/{id}")
    public Mono<? extends ResponseEntity<?>> buscarPorId(@PathVariable(value = "id") String id) {
        if (id.isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return service.buscarPorId(id)
                .map(mercado -> ResponseEntity.ok().body(mercado))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .onErrorResume(error -> Mono.just(ResponseEntity.internalServerError().build()));
    }

    @GetMapping("/nomes")
    public Mono<? extends ResponseEntity<?>> buscarPorNomes(
            @RequestParam(value = "nome") String nome) {
        if (nome.isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return service.buscarPorNome(nome)
                .map(mercados -> ResponseEntity.ok().body(mercados))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(error -> Mono.just(ResponseEntity.internalServerError().build()));
    }
    @GetMapping()
    public Mono<ResponseEntity<Flux<Mercado>>> listarTodos() {
        return service.listarTodos()
                .collectList()
                .map(mercados -> ResponseEntity.ok().body(Flux.fromIterable(mercados)))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> remover(@PathVariable String id){
        return service.remover(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .onErrorResume(
                        e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @GetMapping("/moedas")
    public Mono<ResponseEntity<Moeda>> buscarValorMoeda(
            @RequestParam(value = "moeda") String moeda) {

        if (moeda.isBlank()) {
            return Mono.error(new BadRequestException("Moeda inválida"));
        }

        return service.buscarValorMoeda(moeda)
                .map(valor -> ResponseEntity.ok().body(valor))
                .defaultIfEmpty(ResponseEntity.noContent().build())
                .onErrorResume(
                        e -> Mono.error(new ObjectNotFoundException(e.getMessage())
                        ));
    }

}