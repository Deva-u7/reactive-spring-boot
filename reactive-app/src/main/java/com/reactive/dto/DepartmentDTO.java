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
public class DepartmentDTO {
    Long id;
    String name;
    String location;
    String type;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
