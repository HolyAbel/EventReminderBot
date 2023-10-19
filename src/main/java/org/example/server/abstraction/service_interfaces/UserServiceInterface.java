package org.example.server.abstraction.service_interfaces;

import org.example.server.repository.UserRepo;
import reactor.core.publisher.Mono;

public interface UserServiceInterface {
	Mono<UserDto> getById(Long id);
	Mono<UserDto> getByName(String name);
	Mono<Long> addUser(UserServiceInterface.AddUserDto addUserDto);
	Mono<UserDto> signIn(UserServiceInterface.SignInDto signInDto);

	record UserDto(
		Long id,
		String name,
		String login,
		String password
	){
		public static UserDto fromDbEntity(UserRepo.User user){
			return new UserDto(
				user.id(),
				user.name(),
				user.login(),
				user.password()
			);
		}
	}

	record AddUserDto(
			String name,
			String login,
			String password
	){
		public static UserRepo.User toDbEntity(UserServiceInterface.AddUserDto addUserDto){
			return new UserRepo.User(
					null,
					addUserDto.name(),
					addUserDto.login(),
					addUserDto.password()
			);
		}
	}

	record SignInDto(
			String login,
			String password
	){}
}
