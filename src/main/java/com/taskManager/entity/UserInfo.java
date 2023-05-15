package com.taskManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @Column(unique = true)
    private String username;

    private String password;

    private String roles;  //"ADMIN,USER"

    //@JsonIgnore
    @OneToOne
    @JoinColumn(name = "username_id", nullable = false)
    private User user;

}
