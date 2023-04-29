package tech.ada.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import tech.ada.user.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}