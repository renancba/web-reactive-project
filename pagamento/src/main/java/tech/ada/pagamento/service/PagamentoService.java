package tech.ada.pagamento.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import tech.ada.pagamento.model.*;
import tech.ada.pagamento.repository.TransacaoRepository;

@Service
public class PagamentoService {

    private TransacaoRepository transacaoRepository;

    public PagamentoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Mono<Comprovante> pagar(Pagamento pagamento) {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Flux<Usuario> usuarios = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/usernames") // http://..users/usernames?users=bob,alice
                        .queryParam("users", pagamento.getParamUsuarios())
                        .build())
                .retrieve().bodyToFlux(Usuario.class);

        Mono<Comprovante> comprovanteMono = Flux.zip(usuarios, usuarios.skip(1))
                .flatMap(tupla -> {
                    Usuario user = tupla.getT1();
                    if (user.getBalance() < pagamento.getValor()) {
                        return errorInsufficientBalance(user);
                    } else {
                        return Mono.just(tupla);
                    }
                })
                .map(tupla -> new Transacao(
                        tupla.getT1().getUsername(),
                        tupla.getT2().getUsername(),
                        pagamento.getValor()))
                .last()
                .flatMap(tx -> transacaoRepository.save(tx))
                .map(tx -> new Comprovante(
                        tx.getId(),
                        tx.getPagador(),
                        tx.getRecebedor(),
                        tx.getValor(),
                        tx.getData()))
                .flatMap(cmp -> salvar(cmp));

        return comprovanteMono;
    }

    private Mono<Tuple2<Usuario, Usuario>> errorInsufficientBalance(Usuario user) {
        String message = "Saldo insuficiente para o usuário " + user.getUsername();
        return Mono.error(new RuntimeException(message));
    }


    private Mono<Comprovante> salvar(Comprovante cmp) {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<Comprovante> monoComprovante = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/pagamentos")
                        .build())
                .bodyValue(cmp)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToMono(Comprovante.class);

        monoComprovante.log();

        return Mono.from(monoComprovante);
    }

}