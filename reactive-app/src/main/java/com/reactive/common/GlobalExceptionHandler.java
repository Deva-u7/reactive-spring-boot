package com.reactive.common;

import com.reactive.exception.BadRequestException;
import com.reactive.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseStatusException> handleGeneralException(Exception ex) {
        return Mono.just(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseStatusException> handleGeneralException(RuntimeException ex) {
        return Mono.just(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ResponseStatusException> handleUserNotFoundException(UserNotFoundException ex) {
        return Mono.just(new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseStatusException> handleBadRequestException(BadRequestException ex) {
        return Mono.just(new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
