package com.taskManager.service;

import com.taskManager.entity.Task;
import com.taskManager.modal.TaskModel;
import com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskModel taskModel) {
        Task task = new Task();

        if (Objects.nonNull(taskModel.getName())) task.setName(taskModel.getName());
        if (Objects.nonNull(taskModel.getDescription())) task.setDescription(taskModel.getDescription());
        if (Objects.nonNull(taskModel.getDueDate())) task.setDueDate(taskModel.getDueDate());
        if (Objects.nonNull(taskModel.getPriority())) task.setPriority(taskModel.getPriority());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        if (Objects.nonNull(taskModel.getCreatedBy())) task.setCreatedBy(taskModel.getCreatedBy());
        if (Objects.nonNull(taskModel.getAssignedTo())) task.setAssignedTo(taskModel.getAssignedTo());
        if (Objects.nonNull(taskModel.getProject())) task.setProject(taskModel.getProject());
        if (Objects.nonNull(taskModel.getCategory())) task.setCategory(taskModel.getCategory());

        taskRepository.save(task);
        return task;
    }

    public Task updateTask(TaskModel taskModel, Long id) {
        Task task = taskRepository.findById(id).get();

        if (Objects.nonNull(taskModel.getName())) task.setName(taskModel.getName());
        if (Objects.nonNull(taskModel.getDescription())) task.setDescription(taskModel.getDescription());
        if (Objects.nonNull(taskModel.getDueDate())) task.setDueDate(taskModel.getDueDate());
        if (Objects.nonNull(taskModel.getPriority())) task.setPriority(taskModel.getPriority());
        task.setUpdatedAt(LocalDateTime.now());
        if (Objects.nonNull(taskModel.getCreatedBy())) task.setCreatedBy(taskModel.getCreatedBy());
        if (Objects.nonNull(taskModel.getAssignedTo())) task.setAssignedTo(taskModel.getAssignedTo());
        if (Objects.nonNull(taskModel.getProject())) task.setProject(taskModel.getProject());
        if (Objects.nonNull(taskModel.getCategory())) task.setCategory(taskModel.getCategory());

        taskRepository.save(task);
        return task;
    }

    public List<Task> findTaskByCreatedById(Long id) {
        return taskRepository.findByCreatedById(id);
    }

    public List<Task> findByAssignedToId(Long id){
        return taskRepository.findByAssignedToId(id);
    }

    public List<Task> findByCompleted(boolean bool){
        return taskRepository.findByCompleted(bool);
    }


}
