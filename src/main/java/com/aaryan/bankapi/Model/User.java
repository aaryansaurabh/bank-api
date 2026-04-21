package com.aaryan.bankapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true,nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true)
    private String phoneNo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String gender;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String address;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();
}
