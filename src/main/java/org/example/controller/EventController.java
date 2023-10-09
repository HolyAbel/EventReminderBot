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
	public Mono<Long> findById(@RequestBody EventServiceInterface.AddEventDto addTaskDto) {return eventService.addEvent(addTaskDto);}
}
