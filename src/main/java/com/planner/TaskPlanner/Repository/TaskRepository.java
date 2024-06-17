package com.planner.TaskPlanner.Repository;

import com.planner.TaskPlanner.Entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
