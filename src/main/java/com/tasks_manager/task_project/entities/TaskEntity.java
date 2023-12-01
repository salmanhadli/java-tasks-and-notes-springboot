package com.tasks_manager.task_project.entities;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    // private ArrayList<NoteEntity> notes;
}
