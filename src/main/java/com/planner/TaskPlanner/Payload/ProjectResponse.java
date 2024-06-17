package com.planner.TaskPlanner.Payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private List<ProjectDto> projects;
    private int totalPages;
    private long totalElements;
    private int pageNo;
    private int pageSize;
    private boolean last;


}
