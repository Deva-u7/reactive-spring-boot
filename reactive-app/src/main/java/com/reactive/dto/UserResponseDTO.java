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
public class UserResponseDTO {
    Long id;
    String name;
    String address;
    String email;
    DepartmentDTO department;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
