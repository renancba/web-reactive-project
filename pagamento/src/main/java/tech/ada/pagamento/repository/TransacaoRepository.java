package tech.ada.pagamento.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import tech.ada.pagamento.model.Transacao;

public interface TransacaoRepository extends ReactiveMongoRepository<Transacao, String> {

}