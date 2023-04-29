package com.taskManager.controller;

import com.taskManager.entity.User;
import com.taskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/User")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Long id){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUSer(id);
    }

}
