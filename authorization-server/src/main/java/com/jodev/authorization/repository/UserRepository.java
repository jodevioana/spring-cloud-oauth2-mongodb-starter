package com.jodev.authorization.repository;

import com.jodev.authorization.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

}
