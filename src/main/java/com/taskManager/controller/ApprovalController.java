package com.taskManager.controller;

import com.taskManager.Security.GetUser;
import com.taskManager.entity.User;
import com.taskManager.repository.TaskRepository;
import com.taskManager.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/approval")
@RestController
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

//    @Autowired
//    private GetUser getUser;

    User user;

    @PutMapping("/{id}/{approve}")
    private void approval(@PathVariable Long id,@PathVariable Boolean approve){
        approvalService.approval(id,approve,user);
    }

}
