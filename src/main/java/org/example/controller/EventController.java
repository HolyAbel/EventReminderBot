package org.example.controller;

import org.example.abstraction.sevice_interfaces.EventServiceInterface;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public record EventController(
	EventServiceInterface eventService
) {
	@GetMapping("/getEvent/{id}")
	public Mono<EventServiceInterface.EventDto> findById(@PathVariable Long id){
		return eventService.getById(id);
	}

	@PostMapping("/addEvent")
	public Mono<Long> addEvent(@RequestBody EventServiceInterface.AddEventDto addEventDto) {return eventService.addEvent(addEventDto);}

	@DeleteMapping("/deleteEvent/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {return eventService.deleteEvent(id);}

	@PostMapping("/updateEvent/{id}")
	public Mono<Long> updateById(@RequestBody EventServiceInterface.EditEventDto editEventDto, @PathVariable Long id) {return eventService.updateEvent(editEventDto, id);}
}