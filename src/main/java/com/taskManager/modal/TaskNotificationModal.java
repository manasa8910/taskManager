package com.taskManager.modal;

import com.taskManager.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskNotificationModal {
    private String notificationMessage;
    private String name;
    private String description;
    private Date dueDate;
    private Priority priority;
    private User createdBy;
    private User assignedTo;
    private Project project;
    private Category category;
    private Status status;

}
