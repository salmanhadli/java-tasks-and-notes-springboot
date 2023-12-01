package com.tasks_manager.task_project.DTOs;

import com.tasks_manager.task_project.entities.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponseDTO {
    private NoteEntity note;
    private Integer taskId;
}
