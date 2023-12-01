package com.tasks_manager.task_project.service;

import com.tasks_manager.task_project.entities.TaskEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private ArrayList<TaskEntity> tasks = new ArrayList<>();
  private int taskId = 1;

  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
    "yyyy-MM-dd"
  );

  public TaskEntity addTask(String title, String description, String deadline)
    throws ParseException {
    TaskEntity task = new TaskEntity();
    task.setId(taskId);
    task.setTitle(title);
    task.setDescription(description);
    // task.setDeadline(new Date()); //TODO: validate date format
    task.setDeadline(simpleDateFormat.parse(deadline));
    task.setCompleted(false);
    tasks.add(task);
    this.taskId++;
    return task;
  }

  public ArrayList<TaskEntity> getTasks() {
    return tasks;
  }

  public TaskEntity getTaskById(int id) {
    for (TaskEntity task : tasks) {
      if (task.getId() == id) return task;
    }
    return null;
  }

  public TaskEntity updateTask(
    int id,
    String description,
    String deadline,
    Boolean completed
  ) throws ParseException {
    TaskEntity task = getTaskById(id);
    if (task == null) return null;

    if (description != null) task.setDescription(description);

    if (deadline != null) task.setDeadline(simpleDateFormat.parse(deadline));

    if (completed != null) task.setCompleted(completed);
    return task;
  }
}
