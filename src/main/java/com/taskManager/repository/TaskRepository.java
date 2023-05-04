package com.taskManager.repository;

import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByCompletedAndAssignedTo(boolean completed, User assignedTo);

}
