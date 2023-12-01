package com.tasks_manager.task_project.controllers;

import com.tasks_manager.task_project.DTOs.CreateTaskDTO;
import com.tasks_manager.task_project.DTOs.ErrorResponseDTO;
import com.tasks_manager.task_project.DTOs.TaskResponseDTO;
import com.tasks_manager.task_project.DTOs.UpdateTaskDTO;
import com.tasks_manager.task_project.entities.NoteEntity;
import com.tasks_manager.task_project.entities.TaskEntity;
import com.tasks_manager.task_project.service.NoteService;
import com.tasks_manager.task_project.service.TaskService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController {

  private final TaskService taskService;
  private final NoteService noteService;
  private ModelMapper modelMapper = new ModelMapper();

  public TasksController(TaskService taskService, NoteService noteService) {
    this.taskService = taskService;
    this.noteService = noteService;
  }

  @GetMapping("")
  public ResponseEntity<List<TaskEntity>> getTasks() {
    ArrayList<TaskEntity> tasks = this.taskService.getTasks();
    if (tasks == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(tasks);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDTO> getTaskById(
    @PathVariable("id") Integer id
  ) {
    TaskEntity task = this.taskService.getTaskById(id);
    ArrayList<NoteEntity> notes = this.noteService.getNotesForTask(id);
    if (task == null) {
      return ResponseEntity.notFound().build();
    }
    TaskResponseDTO taskResponse = modelMapper.map(task, TaskResponseDTO.class);
    taskResponse.setNotes(notes);
    // if (notes != null) task.setNotes(notes);
    return ResponseEntity.ok(taskResponse);
  }

  @PostMapping("")
  public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body)
    throws java.text.ParseException {
    return ResponseEntity.ok(
      taskService.addTask(
        body.getTitle(),
        body.getDescription(),
        body.getDeadline()
      )
    );
  }

  @PatchMapping("/{id}")
  public ResponseEntity<TaskEntity> updateTaskEntity(
    @PathVariable Integer id,
    @RequestBody UpdateTaskDTO body
  ) throws java.text.ParseException {
    TaskEntity task = taskService.updateTask(
      id,
      body.getDescription(),
      body.getDeadline(),
      body.getCompleted()
    );
    if (task == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(task);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
    if (e instanceof ParseException) {
      return ResponseEntity
        .badRequest()
        .body(new ErrorResponseDTO("Invalid date format"));
    }

    return ResponseEntity
      .internalServerError()
      .body(new ErrorResponseDTO("Internal Server Error"));
  }
}
