package org.example.abstraction.sevice;

import org.example.abstraction.sevice_interfaces.EventServiceInterface;
import org.example.abstraction.sevice_interfaces.UserServiceInterface;
import org.example.repository.EventRepo;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserRepo userRepo;

	@Override
	public Mono<UserDto> getById(Long id) {
		return userRepo
			.findById(id)
			.map(UserDto::fromDbEntity);
	}

	@Override
	public Mono<Long> addUser(UserServiceInterface.AddUserDto addUserDto) {
		return userRepo
				.save(UserServiceInterface.AddUserDto.toDbEntity(addUserDto))
				.map(UserRepo.User::id);
	}

	@Override
	public Mono<UserDto> signIn(UserServiceInterface.SignInDto signInDto) {
		return userRepo
				.signIn(signInDto.login(), signInDto.password())
				.map(UserDto::fromDbEntity);
	}
}
