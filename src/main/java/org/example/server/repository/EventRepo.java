package org.example.server.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public interface EventRepo extends ReactiveCrudRepository<EventRepo.Event, Long> {
	@Table("event")
	record Event(
		@Id
		Long id,
		Long userId,
		String summary,
		Instant datetime,
		Long duration,
		Integer type,
		Boolean isEnd
	){}

	Flux<EventRepo.Event> findByDatetime(Instant datetime);
	Flux<EventRepo.Event>findByType(Integer type);

	@Query("SELECT * " +
			"FROM event " +
			"WHERE event.datetime > now() " +
			"ORDER BY event.datetime " +
			"LIMIT 1")
	Mono<EventRepo.Event> findNext();

	@Query("SELECT * " +
			"FROM event " +
			"WHERE event.datetime > :datetime " +
			"ORDER BY event.datetime")
	Flux<EventRepo.Event> findAllByDatetimeLessThen(Instant datetime);
}
