package org.example.abstraction.sevice_interfaces;

import org.example.repository.EventRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;

public interface EventServiceInterface {
	Mono<EventDto> getById(Long id);
	Flux<EventDto> getByDatetime(Instant datetime);
	Mono<EventDto> getNext();
	Flux<EventDto> getDay(Instant datetime);
	Flux<EventDto> getWeek(Instant datetime);
	Mono<Long> addEvent(AddEventDto addEventDto);
	Mono<Void> deleteEvent(Long id);
	Mono<Long> updateEvent(EditEventDto editEventDto, Long id);

	record EventDto(
			Long id,
			Long userId,
			String summary,
			Instant datetime,
			Long duration,
			Integer type,
			Boolean isEnd
	){
		public static EventDto fromDbEntity(EventRepo.Event event){
			return new EventDto(
					event.id(),
					event.userId(),
					event.summary(),
					event.datetime(),
					event.duration(),
					event.type(),
					event.isEnd()
			);
		}
	}

	record AddEventDto(
		Long userId,
		String summary,
		Instant datetime,
		Long duration,
		Integer type
	){
		public static EventRepo.Event toDbEntity(AddEventDto addEventDto){
			return new EventRepo.Event(
				null,
				addEventDto.userId(),
				addEventDto.summary(),
				addEventDto.datetime(),
				addEventDto.duration(),
				addEventDto.type(),
				false
			);
		}
	}

	record EditEventDto(
			Long userId,
			String summary,
			Instant datetime,
			Long duration,
			Integer type
	){
		public static EventRepo.Event toDbEntity(EditEventDto editEventDto, Long id){
			return new EventRepo.Event(
					id,
					editEventDto.userId(),
					editEventDto.summary(),
					editEventDto.datetime(),
					editEventDto.duration(),
					editEventDto.type(),
					false
			);
		}
	}
}
