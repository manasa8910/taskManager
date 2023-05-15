package com.taskManager.service;

import com.taskManager.entity.UserInfo;
import com.taskManager.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserInfo addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return repository.save(userInfo);
    }

    public UserInfo update(UserInfo userInfo, String username) {
        UserInfo existingUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"+username));

        if (Objects.nonNull(userInfo.getPassword())) existingUser.setPassword(userInfo.getPassword());
        if (Objects.nonNull(userInfo.getRoles())) existingUser.setRoles(userInfo.getRoles());
        if (Objects.nonNull(userInfo.getUser())) existingUser.setUser(userInfo.getUser());

        return repository.save(existingUser);
    }

    public UserInfo findById(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"+username));
    }

    public List<UserInfo> findAll() {
        return repository.findAll();
    }

    public void deleteById(String username) {
        repository.delete(repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"+username)));
    }
}
