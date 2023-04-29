package tech.ada.user.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ReactiveService {

    public Flux<String> nomes() {
        var nomes =  List.of("Bob", "Alice", "Sam", "John");
        var nomesFlux = Flux.fromIterable(nomes);
        return nomesFlux;
    }

    public Mono<String> nomeMono() {
        return Mono.just("alice");
    }

}