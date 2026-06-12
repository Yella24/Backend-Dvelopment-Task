package com.example.userage.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * JPA entity mirroring the {@code users} table.
 *
 * <p>Why a separate entity from the API DTOs? It keeps the persistence model
 * decoupled from the JSON contract. The DTOs can change without forcing a DB
 * migration and vice-versa.</p>
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    public UserEntity() {
    }

    public UserEntity(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
