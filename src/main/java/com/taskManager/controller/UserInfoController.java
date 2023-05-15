package com.taskManager.controller;

import com.taskManager.entity.UserInfo;
import com.taskManager.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserInfoController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public UserInfo save(@RequestBody UserInfo userInfo) {
        LOGGER.info("creating new userInfo");
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> findById(@PathVariable String username) {
        UserInfo userInfo = userInfoService.findById(username);
        if (userInfo != null) {
            return ResponseEntity.ok(userInfo);
        } else {
            return new ResponseEntity<>("project not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{username}")
    public UserInfo update(@RequestBody UserInfo userInfo, @PathVariable String username) {
        LOGGER.info("updating userInfo with username {}", username);
        return userInfoService.update(userInfo, username);
    }

    @GetMapping
    public List<UserInfo> findAll() {
        return userInfoService.findAll();
    }

    @DeleteMapping("/{username}")
    public void deleteById(@PathVariable String username) {
        LOGGER.info("deleting userinfo with username {}", username);
        userInfoService.deleteById(username);
    }
}
