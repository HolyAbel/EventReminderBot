package org.example.controller;

import org.example.abstraction.sevice_interfaces.EventServiceInterface;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;

@RestController
public record EventController(
	EventServiceInterface eventService
) {
	@GetMapping("/getEvent/{id}")
	public Mono<EventServiceInterface.EventDto> findById(@PathVariable Long id){
		return eventService.getById(id);
	}

//	@GetMapping("/getEvent/Next}")
//	public Mono<EventServiceInterface.EventDto> findNext(@PathVariable Long id){
//		return eventService.getById(id);
//	}

	@GetMapping("/getEvents/{datetime}")
	public Flux<EventServiceInterface.EventDto> findByDatetime(@PathVariable Instant datetime){
		return eventService.getByDatetime(datetime);
	}

	@PostMapping("/addEvent")
	public Mono<Long> addEvent(@RequestBody EventServiceInterface.AddEventDto addEventDto) {return eventService.addEvent(addEventDto);}

	@DeleteMapping("/deleteEvent/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {return eventService.deleteEvent(id);}

	@PostMapping("/updateEvent/{id}")
	public Mono<Long> updateById(@RequestBody EventServiceInterface.EditEventDto editEventDto, @PathVariable Long id) {return eventService.updateEvent(editEventDto, id);}
}