package com.taskManager.entity;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDetails_id", nullable = false)
    private UserDetails userDetails;
}

