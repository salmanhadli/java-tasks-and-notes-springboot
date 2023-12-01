package com.tasks_manager.task_project.DTOs;

import java.util.ArrayList;
import java.util.Date;

import com.tasks_manager.task_project.entities.NoteEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
  private int id;
  private String title;
  private String description;
  private Date deadline;
  private boolean completed;
  private ArrayList<NoteEntity> notes;
}
