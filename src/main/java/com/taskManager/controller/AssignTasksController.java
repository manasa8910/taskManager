package com.taskManager.controller;

import com.taskManager.Config.UserInfoUserDetailsService;
import com.taskManager.entity.Comment;
import com.taskManager.entity.Task;
import com.taskManager.modal.TaskModel;
import com.taskManager.service.AssignTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/assignTask")
@RestController
public class AssignTasksController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AssignTasksController.class);

    @Autowired
    private AssignTaskService assignTaskService;

    @Autowired
    private UserInfoUserDetailsService authService;

    @GetMapping
    public List<Task> getTasks() {
        return assignTaskService.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTask(@PathVariable Long id) {
        Task task = assignTaskService.getTask(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return new ResponseEntity<>("project not found", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public Task createTask(@RequestBody TaskModel taskModel) {
        LOGGER.info("Assigning new task");
        return assignTaskService.createTask(taskModel, authService.performAction());
    }

    @PutMapping("/{id}")
    private Task updateTask(@RequestBody TaskModel taskModel, @PathVariable Long id) {
        LOGGER.info("updating task");
        return assignTaskService.updateTask(taskModel, id, authService.performAction());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        LOGGER.info("deleting task");
        assignTaskService.deleteTask(id, authService.performAction());
    }

    @GetMapping("/comments/{id}")
    public List<Comment> getcommentsByTaskId(@PathVariable Long id) {
        return assignTaskService.getComments(id);
    }

}
