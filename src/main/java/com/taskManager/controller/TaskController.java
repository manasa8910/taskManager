package com.taskManager.controller;

import com.taskManager.entity.Task;
import com.taskManager.modal.TaskModel;
import com.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    //get all Tasks by createdBy
    @GetMapping("/created")
    public List<Task> findTaskByCreatedById(Long id){
        return taskService.findTaskByCreatedById(id);
    }

    //get all tasks by assignedTo
    @GetMapping("/MyTasks")
    public List<Task> findByAssignedToId(Long id){
        return taskService.findByAssignedToId(id);
    }

    //get non completed tasks
    @GetMapping("/MyTasks/active")
    public List<Task> incompleteTasks(){
        return taskService.findByCompleted(false);
    }

    //get completed tasks
    @GetMapping("/MyTasks/inActive")
    public List<Task> completedTasks(){
        return taskService.findByCompleted(true);
    }

    //create a new task
    @PostMapping("/create")
    public Task createTask(@RequestBody TaskModel taskModel) {
        return taskService.createTask(taskModel);
    }

    //update a task
    @PutMapping("/update/{id}")
    public Task updateTask(@RequestBody TaskModel taskModel, @PathVariable Long id) {
        return taskService.updateTask(taskModel, id);
    }
}
