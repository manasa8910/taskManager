package com.taskManager.repository;

import com.taskManager.entity.Comment;
import com.taskManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    public List<Task> findByCreatedById(Long id);

    public List<Task> findByAssignedToId(Long id);

    public List<Task> findByCompleted(boolean bool);

}
