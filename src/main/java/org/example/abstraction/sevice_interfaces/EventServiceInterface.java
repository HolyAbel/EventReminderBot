package org.example.abstraction.sevice_interfaces;

import org.example.repository.EventRepo;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface EventServiceInterface {
	Mono<EventDto> getById(Long id);
	Mono<Long> addEvent(AddEventDto addEventDto);
	Mono<Void> deleteEvent(Long id);
	Mono<Long> updateEvent(EditEventDto editEventDto);

	record AddEventDto(
		Long userId,
		String summary,
		Instant startTime,
		Long duration
	){
		public static EventRepo.Event toDbEntity(AddEventDto addEventDto){
			return new EventRepo.Event(
				null,
				addEventDto.userId(),
				addEventDto.summary(),
				addEventDto.startTime(),
				addEventDto.duration(),
				false
			);
		}
	}

	record EditEventDto(
			Long id,
			Long userId,
			String summary,
			Instant startTime,
			Long duration
	){
		public static EventRepo.Event toDbEntity(EditEventDto editEventDto){
			return new EventRepo.Event(
					editEventDto.id(),
					editEventDto.userId(),
					editEventDto.summary(),
					editEventDto.startTime(),
					editEventDto.duration(),
					false
			);
		}
	}

	record EventDto(
		Long id,
		Long userId,
		String summary,
		Instant startTime,
		Long duration
	){
		public static EventDto fromDbEntity(EventRepo.Event event){
			return new EventDto(
				event.id(),
				event.userId(),
				event.summary(),
				event.startTime(),
				event.duration()
				);
		}
	}

}
