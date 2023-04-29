package com.taskManager.service;

import com.taskManager.entity.Comment;
import com.taskManager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(Long id) {
        return commentRepository.findByTaskId(id);
    }
}
