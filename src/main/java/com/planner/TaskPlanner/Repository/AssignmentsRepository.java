package com.planner.TaskPlanner.Repository;

import com.planner.TaskPlanner.Entities.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
}
