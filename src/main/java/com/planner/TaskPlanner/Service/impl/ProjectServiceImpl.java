package com.planner.TaskPlanner.Service.impl;

import com.planner.TaskPlanner.Payload.ProjectDto;
import com.planner.TaskPlanner.Repository.ProjectRepository;
import com.planner.TaskPlanner.Entities.Projects;
import com.planner.TaskPlanner.Payload.ProjectResponse;
import com.planner.TaskPlanner.Service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Projects projects = mapToEntity(projectDto);
        Projects newProject = projectRepository.save(projects);
        ProjectDto projectResponse = mapToDto(newProject);
        return projectResponse;
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Projects projects = projectRepository.findById(id).get();
        return mapToDto(projects);

    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto, Long id) {
        Projects projects = projectRepository.findById(id).get();
        projects.setName(projectDto.getName());
        projects.setDescription(projectDto.getDescription());
        Projects updatedProject = projectRepository.save(projects);
        return mapToDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Projects projects = projectRepository.findById(id).get();
        projectRepository.delete(projects);
    }

    @Override
    public ProjectResponse getAllProjects(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Projects> projectList = projectRepository.findAll(pageable).getContent();
        List<ProjectDto> projects = projectList.stream().map(this::mapToDto).collect(Collectors.toList());
        ProjectResponse projectResponse = new ProjectResponse(projects, projectList.size(), projectList.size(), pageable.getPageNumber(), pageable.getPageSize(), projectList.isEmpty());
        return projectResponse;

    }

    private ProjectDto mapToDto(Projects projects) {
        ProjectDto projectDto = modelMapper.map(projects, ProjectDto.class);
        return projectDto;
    }

    private Projects mapToEntity(ProjectDto projectDto) {
        Projects projects = modelMapper.map(projectDto, Projects.class);
        return projects;
    }
}
