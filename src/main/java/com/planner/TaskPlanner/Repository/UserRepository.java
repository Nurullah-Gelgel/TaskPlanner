package com.planner.TaskPlanner.Repository;

import com.planner.TaskPlanner.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
