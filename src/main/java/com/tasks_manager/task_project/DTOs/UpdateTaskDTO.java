package com.tasks_manager.task_project.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
  String description;
  String deadline;
  Boolean completed;
}
