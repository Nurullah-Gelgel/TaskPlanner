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
@Table(name = "tasks")
public class
Tasks {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private long project_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "assigned_user_id")
    private int assigned_user_id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_id", insertable = false, referencedColumnName = "id")
//    private Projects project;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "assigned_user_id", insertable = false, referencedColumnName = "id")
//    private Users assigned_user;

    @Column(name = "created_at", updatable = false )
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;
}
