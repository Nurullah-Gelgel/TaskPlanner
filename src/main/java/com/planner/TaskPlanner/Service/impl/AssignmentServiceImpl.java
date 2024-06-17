package com.planner.TaskPlanner.Service.impl;

import com.planner.TaskPlanner.Entities.Assignments;
import com.planner.TaskPlanner.Payload.AssignmentDto;
import com.planner.TaskPlanner.Payload.AssignmentResponse;
import com.planner.TaskPlanner.Repository.AssignmentsRepository;
import com.planner.TaskPlanner.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AssignmentServiceImpl implements AssignmentService {

    private AssignmentsRepository assignmentsRepository;
    @Autowired
    public AssignmentServiceImpl(AssignmentsRepository assignmentsRepository) {
        this.assignmentsRepository = assignmentsRepository;
    }

    @Override
    public AssignmentResponse getAssignments(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<Assignments> assignmentList = assignmentsRepository.findAll(pageable).getContent();
        List<AssignmentDto> assignments = assignmentList.stream().map(this::mapToDto).collect(Collectors.toList());
        AssignmentResponse assignmentResponse = new AssignmentResponse(assignments, assignmentList.size(), assignmentList.size(), pageable.getPageNumber(), pageable.getPageSize(), assignmentList.isEmpty());
        return assignmentResponse;
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Assignments assignment = mapToEntity(assignmentDto);
        Assignments newAssignment = assignmentsRepository.save(assignment);
        AssignmentDto assignmentResponse = mapToDto(newAssignment);
        return assignmentResponse;
    }

    @Override
    public AssignmentDto getAssignmentById(long id) {
        Assignments assignments = assignmentsRepository.findById(id).get();
        return mapToDto(assignments);
    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDto assignmentDto, long id) {
        Assignments assignment = assignmentsRepository.findById(id).get();
        assignment.setRole(assignmentDto.getRole());
        assignment.setProject_id(assignmentDto.getProject_id());
        assignment.setUser_id(assignmentDto.getUser_id());
        Assignments updatedAssignment = assignmentsRepository.save(assignment);
        return mapToDto(updatedAssignment);
    }

    @Override
    public void deleteAssignment(long id) {
        Assignments assignment = assignmentsRepository.findById(id).get();
        assignmentsRepository.delete(assignment);

    }

    private AssignmentDto mapToDto(Assignments assignment) {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setId(assignment.getId());
        assignmentDto.setRole(assignment.getRole());
        assignmentDto.setProject_id(assignment.getProject_id());
        assignmentDto.setUser_id(assignment.getUser_id());
        return assignmentDto;
    }

    private Assignments mapToEntity(AssignmentDto assignmentDto) {
        Assignments assignment = new Assignments();
        assignment.setId(assignmentDto.getId());
        assignment.setRole(assignmentDto.getRole());
        assignment.setProject_id(assignmentDto.getProject_id());
        assignment.setUser_id(assignmentDto.getUser_id());
        return assignment;
    }
}
