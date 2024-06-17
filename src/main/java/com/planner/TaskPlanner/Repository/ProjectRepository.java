package com.planner.TaskPlanner.Repository;

import com.planner.TaskPlanner.Entities.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
}
