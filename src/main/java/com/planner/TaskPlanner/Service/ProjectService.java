package com.planner.TaskPlanner.Service;

import com.planner.TaskPlanner.Payload.ProjectDto;
import com.planner.TaskPlanner.Payload.ProjectResponse;

public interface ProjectService {

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto getProjectById(Long id);

    ProjectDto updateProject(ProjectDto projectDto, Long id);

    void deleteProject(Long id);

    ProjectResponse getAllProjects(int pageNo, int pageSize, String sortBy, String sortOrder);



}
