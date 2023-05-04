package com.taskManager.controller;

import com.taskManager.entity.UserDetails;
import com.taskManager.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public UserDetails save(@RequestBody UserDetails userDetails) {
        return userDetailsService.save(userDetails);
    }

    @GetMapping("/{id}")
    public UserDetails findById(@PathVariable Long id) {
        return userDetailsService.findById(id);
    }

    @PutMapping("/{id}")
    public UserDetails update(@RequestBody UserDetails userDetails, @PathVariable Long id){
        return userDetailsService.update(userDetails,id);
    }

    @GetMapping
    public List<UserDetails> findAll() {
        return userDetailsService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userDetailsService.deleteById(id);
    }
}
