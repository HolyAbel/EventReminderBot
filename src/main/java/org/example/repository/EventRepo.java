package org.example.repository;

import org.example.abstraction.sevice_interfaces.EventServiceInterface;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

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

	Flux<EventServiceInterface.EventDto> findByDatetime(@Param("datetime") Instant datetime);
}
