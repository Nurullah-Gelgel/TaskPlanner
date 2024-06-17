package com.planner.TaskPlanner.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private List<TaskDto> tasks;
    private int totalPages;
    private long totalElements;
    private int pageNo;
    private int pageSize;
    private boolean last;
}
