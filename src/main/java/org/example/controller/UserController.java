package org.example.controller;

import org.example.abstraction.sevice_interfaces.EventServiceInterface;
import org.example.abstraction.sevice_interfaces.UserServiceInterface;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public record UserController(
	UserServiceInterface userService
) {
	@GetMapping("/getUser/{id}")
	public Mono<UserServiceInterface.UserDto> findById(@PathVariable Long id){
		return userService.getById(id);
	}

	@PostMapping("/addUser")
	public Mono<Long> addUser(@RequestBody UserServiceInterface.AddUserDto addUserDto) {return userService.addUser(addUserDto);}
}
