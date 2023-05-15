package com.taskManager.controller;

import com.taskManager.entity.User;
import com.taskManager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "userName") String userName) {
        User user = userService.getUserById(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>("project not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        LOGGER.info("creating new user");
        return userService.createUser(user);
    }

    @PutMapping("/{userName}")
    public User updateUser(@PathVariable(value = "userName") String userName,
                           @RequestBody User userDetails) {
        LOGGER.info("updating user with username {}", userName);
        return userService.updateUser(userName, userDetails);
    }

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable(value = "userName") String userName) {
        LOGGER.info("deleting user with username {}", userName);
        userService.deleteUser(userName);
    }
}
