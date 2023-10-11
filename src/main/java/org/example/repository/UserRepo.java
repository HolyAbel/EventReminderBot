package org.example.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveCrudRepository<UserRepo.User, Long> {

	@Table("user")
	record User(
		@Id
		Long id,
		String name,
		String login,
		String password
	){}

	@Query("SELECT * FROM user WHERE user.login = :login AND user.password = :password")
	Mono<UserRepo.User> signIn(@Param("login") String login, @Param("password") String password);
}
