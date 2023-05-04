package com.taskManager.controller;

import com.taskManager.Security.GetUser;
import com.taskManager.entity.Comment;
import com.taskManager.entity.Status;
import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import com.taskManager.service.MyTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/myTasks")
@RestController
public class MyTasksController {

    @Autowired
    private MyTasksService myTasksService;

//    @Autowired
//    private GetUser getUser;

    User user;

    //get non completed tasks
    @GetMapping("/active")
    public List<Task> incompleteTasks() {
        return myTasksService.findByCompleted(false, user);
    }

    //get completed tasks
    @GetMapping("/inActive")
    public List<Task> completedTasks() {
        return myTasksService.findByCompleted(true, user);
    }

    //change status
    @PutMapping("/ChangeStatus/{id}")
    public void setStatus(@RequestBody Status status,@PathVariable Long id) {
        myTasksService.setStatus(status, user, id);
    }

    @PostMapping("/comment/{id}")
    public void setComment(@RequestBody Comment comment,@PathVariable Long id) {
        myTasksService.setComment(comment,id);
    }
}


