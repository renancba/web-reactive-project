package tech.ada.user.repository;

import reactor.core.publisher.Flux;
import tech.ada.user.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByUsernameIn(List<String> usernames);

}