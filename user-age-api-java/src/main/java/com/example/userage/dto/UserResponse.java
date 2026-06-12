package com.example.userage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

/**
 * Response DTO. {@code age} is computed at read time and omitted on
 * create/update responses to match the spec.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private Integer age; // nullable: serialized only when present

    public UserResponse() {
    }

    public UserResponse(Long id, String name, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
