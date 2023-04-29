package com.taskManager.modal;

import com.taskManager.entity.Category;
import com.taskManager.entity.Priority;
import com.taskManager.entity.Project;
import com.taskManager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private Priority priority;
    private User createdBy;
    private User assignedTo;
    private Project project;
    private Category category;
}
