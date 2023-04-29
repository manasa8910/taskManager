package com.taskManager.service;

import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import com.taskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Long id) {
        User updateUser = userRepository.findById(id).get();
        if (Objects.nonNull(user.getFullName())) updateUser.setFullName(user.getFullName());
        if (Objects.nonNull(user.getUserDetails())) updateUser.setUserDetails(user.getUserDetails());

        return userRepository.save(updateUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUSer(Long id){
        userRepository.deleteById(id);
    }
}
