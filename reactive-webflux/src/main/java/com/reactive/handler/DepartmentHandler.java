package com.reactive.handler;

import com.reactive.dto.DepartmentDTO;
import com.reactive.exception.NotFoundException;
import com.reactive.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentHandler {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;


    public Mono<ServerResponse> getDepartmentById(ServerRequest request){
        Long departmentId = Long.valueOf(request.pathVariable("departmentId"));
        Mono<DepartmentDTO> response = departmentRepository.findById(departmentId)
                .switchIfEmpty(Mono.error(new NotFoundException(departmentId)))
                .map(department -> modelMapper.map(department, DepartmentDTO.class));
      return  ServerResponse
              .ok().body(response, DepartmentDTO.class);
    }

    public Mono<ServerResponse> getAllDepartments(ServerRequest request){
        Flux<DepartmentDTO> response = departmentRepository.findAll()
                .map(department -> modelMapper.map(department, DepartmentDTO.class));
        return  ServerResponse.ok().body(response, DepartmentDTO.class);
    }

}
