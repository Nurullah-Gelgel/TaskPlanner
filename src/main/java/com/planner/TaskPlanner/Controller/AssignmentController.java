package com.planner.TaskPlanner.Controller;

import com.planner.TaskPlanner.Payload.AssignmentDto;
import com.planner.TaskPlanner.Payload.AssignmentResponse;
import com.planner.TaskPlanner.Service.AssignmentService;
import com.planner.TaskPlanner.utils.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }
    @GetMapping("/all")
    public AssignmentResponse getAllAssignments(@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                                @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                                @RequestParam(name = "sortOrder", defaultValue = AppConstants.DEFAULT_SORT_ORDER) String sortOrder){
        return assignmentService.getAssignments(pageNo, pageSize, sortBy, sortOrder);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDto> getAssignmentById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssignmentDto> createAssignment(@RequestBody AssignmentDto assignmentDto) {
        return ResponseEntity.ok(assignmentService.createAssignment(assignmentDto));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AssignmentDto> updateAssignment(@RequestBody AssignmentDto assignmentDto, @RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(assignmentService.updateAssignment(assignmentDto, id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAssignment(@RequestParam(name = "id") Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.ok("Assignment deleted successfully");
    }


}
