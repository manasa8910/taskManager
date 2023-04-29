package com.taskManager.entity;


import jakarta.persistence.*;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String UserName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "userDetails")
    private User user;
}
