package com.reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("user")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class User {
    @Id
    Long id;

    @Column("name")
    String name;

    @Column("address")
    String address;

    @Column("email")
    String email;

    @Column("department_id")
    Long departmentId;

    @Column("created_at")
    LocalDateTime createdAt;

    @Column("updated_at")
    LocalDateTime updatedAt;
}
