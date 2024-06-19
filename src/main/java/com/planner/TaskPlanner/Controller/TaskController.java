package com.planner.TaskPlanner.Controller;

import com.planner.TaskPlanner.Payload.TaskDto;
import com.planner.TaskPlanner.Payload.TaskResponse;
import com.planner.TaskPlanner.Service.TaskService;
import com.planner.TaskPlanner.utils.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private  TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public TaskResponse getAllTasks(@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                    @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                    @RequestParam(name = "sortOrder", defaultValue = AppConstants.DEFAULT_SORT_ORDER) String sortOrder) {
        return taskService.getAllTasks(pageNo, pageSize, sortBy, sortOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(taskService.updateTask(taskDto, id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTask(@RequestParam(name = "id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

}
