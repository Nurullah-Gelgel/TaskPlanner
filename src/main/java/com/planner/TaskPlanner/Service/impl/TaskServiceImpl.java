package com.planner.TaskPlanner.Service.impl;

import com.planner.TaskPlanner.Entities.Tasks;
import com.planner.TaskPlanner.Exception.ResourceNotFoundException;
import com.planner.TaskPlanner.Payload.TaskDto;
import com.planner.TaskPlanner.Payload.TaskResponse;
import com.planner.TaskPlanner.Repository.TaskRepository;
import com.planner.TaskPlanner.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Tasks task = mapToEntity(taskDto);
        Tasks newTask = taskRepository.save(task);

        TaskDto taskResponse = mapToDto(newTask);
        return taskResponse;
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Tasks task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        return mapToDto(task);

    }
    @Override
    public TaskResponse getAllTasks(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        List<Tasks> taskList = taskRepository.findAll(pageable).getContent();
        List<TaskDto> tasks = taskList.stream().map(this::mapToDto).collect(Collectors.toList());
        TaskResponse taskResponse = new TaskResponse(tasks, taskList.size(), taskList.size(), pageable.getPageNumber(), pageable.getPageSize(), taskList.isEmpty());
        return taskResponse;
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Long id) {
        Tasks task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        task.setDescription(taskDto.getDescription());
        task.setProject_id(taskDto.getProject_id());
        task.setStatus(taskDto.getStatus());
        task.setName(taskDto.getName());

        Tasks updatedTask = taskRepository.save(task);
        return mapToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {

        Tasks task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        taskRepository.delete(task);
    }



    private TaskDto mapToDto(Tasks task) {

        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setProject_id(task.getProject_id());
        taskDto.setStatus(task.getStatus());
        taskDto.setName(task.getName());

        return taskDto;
    }

    private Tasks mapToEntity(TaskDto taskDto) {

        Tasks task = new Tasks();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        task.setProject_id(taskDto.getProject_id());
        task.setStatus(taskDto.getStatus());
        task.setName(taskDto.getName());

        return task;
    }
}
