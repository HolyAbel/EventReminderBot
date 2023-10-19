package org.example.sever.abstraction.sevice;

import org.example.sever.abstraction.sevice_interfaces.EventServiceInterface;
import org.example.sever.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

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
	public Flux<EventDto> getByDatetime(Instant datetime) {
		return eventRepo
				.findByDatetime(datetime)
				.map(EventDto::fromDbEntity);
	}

	@Override
	public Flux<EventDto> getByType(Integer type) {
		return eventRepo
				.findByType(type)
				.map(EventDto::fromDbEntity);
	}

	@Override
	public Mono<EventDto> getNext() {
		return eventRepo
				.findNext()
				.map(EventDto::fromDbEntity);
	}

	@Override
	public Flux<EventDto> getDay(Instant datetime) {
		return eventRepo
				.findAllByDatetimeLessThen(datetime.plusSeconds(86400))
				.map(EventDto::fromDbEntity);
	}

	@Override
	public Flux<EventDto> getWeek(Instant datetime) {
		return eventRepo
				.findAllByDatetimeLessThen(datetime.plusSeconds(604800))
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
	public Mono<Long> updateEvent(EditEventDto editEventDto, Long id) {
		return eventRepo
				.save(EditEventDto.toDbEntity(editEventDto, id))
				.map(EventRepo.Event::id);
	}
}
