package com.taskManager.service;

import com.taskManager.entity.User;
import com.taskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String userName, User user) {
        User existingUser = userRepository.findByUserName(userName);
        existingUser.setFullName(user.getFullName());
        return  userRepository.save(existingUser);
    }

    public void deleteUser(String userName) {
        User user = userRepository.findByUserName(userName);
        userRepository.delete(user);
    }
}

