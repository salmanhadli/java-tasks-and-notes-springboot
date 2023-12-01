package com.tasks_manager.task_project.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO- data transfer object
@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDTO {
    String title;
    String description;
    String deadline;
}
