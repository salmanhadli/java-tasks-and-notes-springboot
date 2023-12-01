package com.tasks_manager.task_project.controllers;

import com.tasks_manager.task_project.DTOs.CreateNoteDTO;
import com.tasks_manager.task_project.DTOs.CreateNoteResponseDTO;
import com.tasks_manager.task_project.entities.NoteEntity;
import com.tasks_manager.task_project.service.NoteService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

  private NoteService noteService;

  public NotesController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping("")
  public ResponseEntity<List<NoteEntity>> getNotes(
    @PathVariable("taskId") Integer taskId
  ) {
    ArrayList<NoteEntity> notes = this.noteService.getNotesForTask(taskId);
    return ResponseEntity.ok(notes);
  }

  @PostMapping("")
  public ResponseEntity<CreateNoteResponseDTO> postMethodName(
    @PathVariable("taskId") Integer taskId,
    @RequestBody CreateNoteDTO body
  ) {
    NoteEntity note = noteService.addNoteForTask(
      taskId,
      body.getTitle(),
      body.getBody()
    );

    return ResponseEntity.ok(new CreateNoteResponseDTO(note, taskId));
  }
}
