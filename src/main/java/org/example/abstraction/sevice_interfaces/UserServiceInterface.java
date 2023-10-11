package org.example.abstraction.sevice_interfaces;

import org.example.repository.EventRepo;
import org.example.repository.UserRepo;
import reactor.core.publisher.Mono;

public interface UserServiceInterface {
	Mono<UserDto> getById(Long id);
	Mono<Long> addUser(UserServiceInterface.AddUserDto addUserDto);

	record UserDto(
		Long id,
		String name
	){
		public static UserDto fromDbEntity(UserRepo.User user){
			return new UserDto(
				user.id(),
				user.name()
			);
		}
	}

	record AddUserDto(
			String name
	){
		public static UserRepo.User toDbEntity(UserServiceInterface.AddUserDto addUserDto){
			return new UserRepo.User(
					null,
					addUserDto.name()
			);
		}
	}
}
