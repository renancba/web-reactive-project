package tech.ada.pagamento.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.ada.pagamento.model.Comprovante;
import tech.ada.pagamento.model.Pagamento;
import tech.ada.pagamento.model.Usuario;
import tech.ada.pagamento.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
@Slf4j
public class PagamentoController {

    private PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<Comprovante> pagar(@RequestBody Pagamento pagamento) {
        return service.pagar(pagamento);
    }

}