package tech.ada.user.service;

import org.springframework.http.ResponseEntity;
import tech.ada.user.exception.UserNotFoundException;
import tech.ada.user.model.User;
import tech.ada.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<User> salvar(User user) {
        return repository.save(user);
    }

    public Flux<User> listar() {
        return repository.findAll();
    }

    public Mono<User> atualizar(User user, String id) {
        return repository.findById(id)
            .flatMap( atual -> repository.save(atual.update(user)));
    }

    public Mono<?> remover(String id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("user not found id " + id)))
            .flatMap(u -> repository.deleteById(id))
            .then();
    }

    public Mono<User> buscarPorId(String id) {
        return repository.findById(id);
    }

    public Flux<User> buscarPorUsernames(String ... users) {
        return repository.findByUsernameIn(Arrays.asList(users));
    }

}