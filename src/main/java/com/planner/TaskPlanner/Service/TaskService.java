package com.planner.TaskPlanner.Service;

import com.planner.TaskPlanner.Payload.TaskDto;
import com.planner.TaskPlanner.Payload.TaskResponse;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(TaskDto taskDto, Long id);

    void deleteTask(Long id);

    TaskResponse getAllTasks(int pageNo, int pageSize, String sortBy, String sortOrder);
}
