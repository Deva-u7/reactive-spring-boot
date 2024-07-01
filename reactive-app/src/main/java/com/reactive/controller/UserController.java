package com.reactive.controller;

import com.reactive.dto.UserRequestDTO;
import com.reactive.dto.UserResponseDTO;
import com.reactive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<UserRequestDTO> saveUser(@RequestBody UserRequestDTO request) {
        return userService.saveUser(request);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<UserResponseDTO> getByUserId(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserRequestDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    public Mono<UserRequestDTO> updateUser(@RequestBody UserRequestDTO request) {
        return userService.updateUser(request);
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}
