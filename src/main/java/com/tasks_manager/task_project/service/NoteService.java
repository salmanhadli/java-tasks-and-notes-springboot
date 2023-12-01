package com.tasks_manager.task_project.service;

import com.tasks_manager.task_project.entities.NoteEntity;
import com.tasks_manager.task_project.entities.TaskEntity;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class NoteService {

  private TaskService taskService;
  private HashMap<Integer, TaskNotesHandler> taskNoteHolders = new HashMap<>();

  public NoteService(TaskService taskService) {
    this.taskService = taskService;
  }

  class TaskNotesHandler {

    protected int noteId = 1;
    protected ArrayList<NoteEntity> notes = new ArrayList<>();
  }

  public ArrayList<NoteEntity> getNotesForTask(int taskId) {
    TaskEntity task = taskService.getTaskById(taskId);
    if (task == null) return null;

    if (!this.taskNoteHolders.containsKey(taskId)) {
      taskNoteHolders.put(taskId, new TaskNotesHandler());
    }

    return taskNoteHolders.get(taskId).notes;
  }

  public NoteEntity addNoteForTask(int taskId, String title, String body) {
    TaskEntity task = this.taskService.getTaskById(taskId);

    if (task == null) return null;

    if (!taskNoteHolders.containsKey(taskId)) {
      taskNoteHolders.put(taskId, new TaskNotesHandler());
    }

    TaskNotesHandler taskNotesHandler = taskNoteHolders.get(taskId);
    NoteEntity note = new NoteEntity();
    note.setId(taskNotesHandler.noteId);
    note.setTitle(title);
    note.setBody(body);

    taskNotesHandler.notes.add(note);
    taskNotesHandler.noteId++;
    return note;
  }
}
