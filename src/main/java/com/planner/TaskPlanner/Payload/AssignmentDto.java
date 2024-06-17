package com.planner.TaskPlanner.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {

    private long id;

    private long project_id;

    private long user_id;

    private String role;




}
