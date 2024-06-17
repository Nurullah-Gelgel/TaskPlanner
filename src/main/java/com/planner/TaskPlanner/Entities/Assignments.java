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
@Table(name = "assignments")
public class Assignments {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long project_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "assigned_at", updatable = false )
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assigned_at;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_id", insertable = false, referencedColumnName = "id")
//    private Projects project;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", insertable = false, referencedColumnName = "user_id")
//    private Users user;
}
