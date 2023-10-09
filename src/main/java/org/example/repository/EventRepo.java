package org.example.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface EventRepo extends ReactiveCrudRepository<EventRepo.Event, Long> {

	@Table("events")
	record Event(
		@Id
		Long id,
		Long userId,
		String summary,
		Instant startTime,
		Long duration,
		Boolean isEnd
	){}
}
