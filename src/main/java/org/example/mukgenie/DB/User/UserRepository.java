package org.example.mukgenie.DB.User;

import org.example.mukgenie.DB.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}