package com.planner.TaskPlanner.Service;

import com.planner.TaskPlanner.Payload.AssignmentDto;
import com.planner.TaskPlanner.Payload.AssignmentResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse getAssignments(int pageNo, int pageSize, String sortBy, String sortOrder);
    AssignmentDto createAssignment(AssignmentDto assignmentDto);
    AssignmentDto getAssignmentById(long id);
    AssignmentDto updateAssignment(AssignmentDto assignmentDto, long id);
    void deleteAssignment(long id);


}
