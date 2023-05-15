package com.taskManager.controller;

import com.taskManager.Config.UserInfoUserDetailsService;
import com.taskManager.entity.Comment;
import com.taskManager.entity.Status;
import com.taskManager.entity.Task;
import com.taskManager.service.MyTasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/myTasks")
@RestController
public class MyTasksController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MyTasksController.class);

    @Autowired
    private MyTasksService myTasksService;

    @Autowired
    private UserInfoUserDetailsService authService;

    //get non completed tasks
    @GetMapping("/active")
    public List<Task> incompleteTasks() {
        return myTasksService.findByCompleted(false, authService.performAction());
    }

    //get completed tasks
    @GetMapping("/inActive")
    public List<Task> completedTasks() {
        return myTasksService.findByCompleted(true, authService.performAction());
    }

    //change status
    @PutMapping("/ChangeStatus/{id}")
    public void setStatus(@RequestBody Status status,@PathVariable Long id) {
        LOGGER.info("changing status");
        myTasksService.setStatus(status, authService.performAction(), id);
    }

    @PostMapping("/comment/{id}")
    public void setComment(@RequestBody Comment comment,@PathVariable Long id) {
        LOGGER.info("adding a comment");
        myTasksService.setComment(comment,id);
    }
}


