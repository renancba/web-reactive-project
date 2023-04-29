package tech.ada.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.user.model.User;
import tech.ada.user.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private UserService service;

    public UserController(UserService clienteService) {
        this.service = clienteService;
    }

    @PostMapping
    public ResponseEntity<User> salvar(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping // TODO nao retorna noContent quando lista vazia
    public ResponseEntity<List<User>> listar() {
        return service.listar()
            .map( users -> ResponseEntity.ok().body(users))
            .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return service.buscarPorId(id)
            .map( u -> ResponseEntity.ok().body(u))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@RequestBody UserRequest user, @PathVariable String id) {
        return service.atualizar(user.create(), id)
            .map(atual -> ResponseEntity.ok().body(atual))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // TODO notFound nao funciona quando ID inexistente
    public ResponseEntity<User> remover(@PathVariable String id) {
        return null;
//        return service.remover(id)
//            .map( user -> ResponseEntity.ok())
//            .or(null);
    }

}