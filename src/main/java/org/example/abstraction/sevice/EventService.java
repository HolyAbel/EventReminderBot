package org.example.abstraction.sevice;

import org.example.abstraction.sevice_interfaces.EventServiceInterface;
import org.example.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EventService implements EventServiceInterface {

	@Autowired
	EventRepo eventRepo;

	@Override
	public Mono<EventDto> getById(Long id) {
		return eventRepo
			.findById(id)
			.map(EventDto::fromDbEntity);
	}

	@Override
	public Mono<Long> addEvent(AddEventDto addEventDto) {
		return eventRepo
			.save(AddEventDto.toDbEntity(addEventDto))
			.map(EventRepo.Event::id);
	}

	@Override
	public Mono<Void> deleteEvent(Long id) {
		return eventRepo
				.deleteById(id);
	}

	@Override
	public Mono<Long> updateEvent(EditEventDto editEventDto) {
		return eventRepo
				.save(EditEventDto.toDbEntity(editEventDto))
				.map(EventRepo.Event::id);
	}
}
