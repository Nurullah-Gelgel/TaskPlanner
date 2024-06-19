package com.planner.TaskPlanner.Controller;

import com.planner.TaskPlanner.Entities.Projects;
import com.planner.TaskPlanner.Payload.ProjectDto;
import com.planner.TaskPlanner.Payload.ProjectResponse;
import com.planner.TaskPlanner.Service.ProjectService;
import com.planner.TaskPlanner.utils.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ProjectResponse getAllProjects(@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                          @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                          @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                          @RequestParam(name = "sortOrder", defaultValue = AppConstants.DEFAULT_SORT_ORDER) String sortOrder) {
        return projectService.getAllProjects(pageNo, pageSize, sortBy, sortOrder);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.createProject(projectDto));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto, @RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(projectService.updateProject(projectDto, id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProject(@RequestParam(name = "id") Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }

}
