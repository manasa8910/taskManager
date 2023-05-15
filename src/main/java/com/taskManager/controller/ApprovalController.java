package com.taskManager.controller;

import com.taskManager.Config.UserInfoUserDetailsService;
import com.taskManager.service.ApprovalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/approval")
@RestController
public class ApprovalController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ApprovalController.class);

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private UserInfoUserDetailsService authService;

    @PutMapping("/{id}/{approve}")
    private void approval(@PathVariable Long id,@PathVariable Boolean approve){
        approvalService.approval(id,approve,authService.performAction());
        LOGGER.info("Approval processing for {}",id);
    }

}
