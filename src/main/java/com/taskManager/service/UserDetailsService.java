package com.taskManager.service;

import com.taskManager.entity.UserDetails;
import com.taskManager.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails save(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails update(UserDetails userDetails, Long id) {
        UserDetails existingUser = userDetailsRepository.findById(id).get();

        if (Objects.nonNull(userDetails.getUserName())) existingUser.setUserName(userDetails.getUserName());
        if (Objects.nonNull(userDetails.getPassword())) existingUser.setPassword(userDetails.getPassword());
        if (Objects.nonNull(userDetails.getIsActive())) existingUser.setIsActive(userDetails.getIsActive());
        if (Objects.nonNull(userDetails.getRole())) existingUser.setRole(userDetails.getRole());
        if (Objects.nonNull(userDetails.getUser())) existingUser.setUser(userDetails.getUser());

        return userDetailsRepository.save(existingUser);
    }

    public UserDetails findById(Long id) {
        return userDetailsRepository.findById(id).orElse(null);
    }

    public List<UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }

    public void deleteById(Long id) {
        userDetailsRepository.deleteById(id);
    }
}
