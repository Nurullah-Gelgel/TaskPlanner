package com.planner.TaskPlanner.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "username", nullable = false)
        private String username;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "role", nullable = false)
        private String role;

        @Column(name = "created_at", updatable = false )
        @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime created_at;

        @Column(name = "updated_at")
        @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime updated_at;
}
