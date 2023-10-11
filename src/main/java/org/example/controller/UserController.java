package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

	@PostMapping("/signUp")
	public Mono<Long> signUp(@RequestBody UserServiceInterface.AddUserDto addUserDto) {return userService.addUser(addUserDto);}

	@PostMapping("/signIn")
	public Mono<UserServiceInterface.UserDto> signIn(@RequestBody UserServiceInterface.SignInDto signInDto, HttpServletResponse response) {
		Mono<UserServiceInterface.UserDto> user = userService.signIn(signInDto);
		if (user != null) {
			Cookie userCookie = new Cookie("userId", String.valueOf(user.map(UserServiceInterface.UserDto::id)));

			userCookie.setMaxAge(3600);
			userCookie.setPath("/");

			response.addCookie(userCookie);
		}

		return user;
	}
}
