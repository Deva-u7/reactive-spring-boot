package com.reactive.service;

import com.reactive.dto.UserRequestDTO;
import com.reactive.dto.UserResponseDTO;
import com.reactive.client.DepartmentWebClient;
import com.reactive.entity.User;
import com.reactive.exception.BadRequestException;
import com.reactive.exception.UserNotFoundException;
import com.reactive.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final DepartmentWebClient departmentWebClient;


    public Mono<UserRequestDTO> saveUser(UserRequestDTO userRequestDTO) {;
        log.info(Thread.currentThread().getName());
        return userRepository.save(modelMapper.map(userRequestDTO, User.class))
                .doOnNext(data -> log.info(Thread.currentThread().getName()))
                .onErrorMap(ex -> new RuntimeException("Error while saving the user : " + ex.getMessage()))
                .map(user -> modelMapper.map(user, UserRequestDTO.class))
                .log();
    }

    public Mono<UserResponseDTO> getUserInfo(Long userId) {
        log.info(Thread.currentThread().getName());
        return userRepository.findById(userId)
                .doOnNext(data -> log.info(Thread.currentThread().getName()))
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)))
                .flatMap(user -> departmentWebClient.getDepartmentById(user.getDepartmentId()).doOnNext(data -> log.info(Thread.currentThread().getName()))
                        .map(department -> {
                            UserResponseDTO userRequestDTO = modelMapper.map(user, UserResponseDTO.class);
                            userRequestDTO.setDepartment(department);
                            return userRequestDTO;
                        })
                )
                .onErrorResume(e -> {
                    if (e instanceof UserNotFoundException) {
                        return Mono.error(e);
                    }
                    return Mono.error(new Exception("An error occurred while fetching the user or department :"+e.getMessage(), e));
                }).log();
    }

    public Flux<UserRequestDTO> getAllUsers() {
         log.info(Thread.currentThread().getName());
         return userRepository.findAll()
                 .switchIfEmpty(Mono.error(new UserNotFoundException(1L)))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(data -> log.info(Thread.currentThread().getName()))
                .map(user -> modelMapper.map(user, UserRequestDTO.class));
    }

    public Mono<UserRequestDTO> updateUser(UserRequestDTO request) {
        if (request.getId() == null) {
            return Mono.error(new BadRequestException("User Id must not be null"));
        }
        return userRepository.findById(request.getId())
                .switchIfEmpty(Mono.error(new UserNotFoundException(request.getId())))
                .flatMap(user -> {
                    user.setName(request.getName());
                    user.setEmail(request.getEmail());
                    user.setAddress(request.getAddress());
                    user.setDepartmentId(request.getDepartmentId());
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user)
                            .onErrorMap(ex -> new RuntimeException("Error while updating the user : " + ex.getMessage()))
                            .map(user1 -> modelMapper.map(user1, UserRequestDTO.class));
                });
    }

    public Mono<Void> deleteUser(Long userId) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new UserNotFoundException(userId)))
                .flatMap(userRepository::delete);
    }
}
