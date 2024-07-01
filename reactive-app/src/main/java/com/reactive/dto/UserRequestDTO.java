package com.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequestDTO {
    Long id;
    String name;
    String address;
    String email;
    Long departmentId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
