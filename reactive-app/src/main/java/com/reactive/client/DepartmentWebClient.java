package com.reactive.client;


import com.reactive.dto.DepartmentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DepartmentWebClient {

    private WebClient webClient;


    public DepartmentWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<DepartmentDTO> getDepartmentById(Long id) {
        return webClient.get()
                .uri("/dept/{departmentId}", id)
                .retrieve()
                .bodyToMono(DepartmentDTO.class);
    }


}

