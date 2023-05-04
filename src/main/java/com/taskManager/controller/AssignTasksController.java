package com.taskManager.controller;

import com.taskManager.Security.GetUser;
import com.taskManager.entity.Comment;
import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import com.taskManager.modal.TaskModel;
import com.taskManager.service.AssignTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/assignTask")
@RestController
public class AssignTasksController {

    @Autowired
    private AssignTaskService assignTaskService;

//    @Autowired
//    private GetUser getUser;

    User user;

    @GetMapping
    public List<Task> getTasks() {
        return assignTaskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return assignTaskService.getTask(id);
    }

    @PostMapping
    public Task createTask(@RequestBody TaskModel taskModel) {
        return assignTaskService.createTask(taskModel, taskModel.getCreatedBy());
    }

    @PutMapping("/{id}")
    private Task updateTask(@RequestBody TaskModel taskModel, @PathVariable Long id) {
        return assignTaskService.updateTask(taskModel, id, taskModel.getCreatedBy());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        assignTaskService.deleteTask(id, user);
    }

    @GetMapping("/comments/{id}")
    public List<Comment> getcommentsByTaskId(@PathVariable Long id){
        return assignTaskService.getComments(id);
    }

}
