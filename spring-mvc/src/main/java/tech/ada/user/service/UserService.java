package tech.ada.user.service;

import org.springframework.stereotype.Service;
import tech.ada.user.model.User;
import tech.ada.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User salvar(User user) {
        return repository.save(user);
    }

    public Optional<List<User>> listar() {
        return Optional.of(repository.findAll());
    }

    public Optional<User> atualizar(User user, String id) {
        return repository.findById(id)
            .map( atual -> repository.save(atual.update(user)));
    }

    public Optional<User> remover(String id) {
        return repository.findById(id)
            .map(user -> {
                repository.deleteById(id);
                return user;
            });
    }

    public Optional<User> buscarPorId(String id) {
        return repository.findById(id);
    }
}