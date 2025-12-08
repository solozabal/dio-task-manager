package com.dio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=50, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;
}