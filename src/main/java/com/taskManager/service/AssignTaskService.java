package com.taskManager.service;

import com.taskManager.entity.Comment;
import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import com.taskManager.kafka.JsonKafkaProducer;
import com.taskManager.modal.TaskModel;
import com.taskManager.modal.TaskNotificationModal;
import com.taskManager.repository.CommentRepository;
import com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AssignTaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JsonKafkaProducer kafkaProducer;

    public Task createTask(TaskModel taskModel, User user) {
        Task task = new Task();
        if (Objects.nonNull(taskModel.getName())) task.setName(taskModel.getName());
        if (Objects.nonNull(taskModel.getDescription())) task.setDescription(taskModel.getDescription());
        if (Objects.nonNull(taskModel.getDueDate())) task.setDueDate(taskModel.getDueDate());
        if (Objects.nonNull(taskModel.getPriority())) task.setPriority(taskModel.getPriority());
        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());
        task.setCreatedBy(user);
        if (Objects.nonNull(taskModel.getAssignedTo())) task.setAssignedTo(taskModel.getAssignedTo());
        if (Objects.nonNull(taskModel.getProject())) task.setProject(taskModel.getProject());
        if (Objects.nonNull(taskModel.getCategory())) task.setCategory(taskModel.getCategory());

        TaskNotificationModal notificationModal = new TaskNotificationModal();
        notificationModal.setNotificationMessage("created a new task");
        notificationModal.setName(task.getName());
        notificationModal.setDescription(task.getDescription());
        notificationModal.setDueDate(task.getDueDate());
        notificationModal.setPriority(task.getPriority());
        notificationModal.setCreatedBy(task.getCreatedBy());
        notificationModal.setAssignedTo(task.getAssignedTo());
        notificationModal.setProject(task.getProject());
        notificationModal.setCategory(task.getCategory());
        notificationModal.setStatus(task.getStatus());

        kafkaProducer.sendMessage(notificationModal);

        return taskRepository.save(task);
    }

    public Task updateTask(TaskModel taskModel, Long id, User user) {
        Task task = taskRepository.findById(id).get();
        if (task.getCreatedBy().equals(user)) {
            if (Objects.nonNull(taskModel.getName())) task.setName(taskModel.getName());
            if (Objects.nonNull(taskModel.getDescription())) task.setDescription(taskModel.getDescription());
            if (Objects.nonNull(taskModel.getDueDate())) task.setDueDate(taskModel.getDueDate());
            if (Objects.nonNull(taskModel.getPriority())) task.setPriority(taskModel.getPriority());
            task.setUpdatedAt(new Date());
            if (Objects.nonNull(taskModel.getAssignedTo())) task.setAssignedTo(taskModel.getAssignedTo());
            if (Objects.nonNull(taskModel.getProject())) task.setProject(taskModel.getProject());
            if (Objects.nonNull(taskModel.getCategory())) task.setCategory(taskModel.getCategory());

            TaskNotificationModal notificationModal = new TaskNotificationModal();
            notificationModal.setNotificationMessage("task has been updated");
            notificationModal.setName(task.getName());
            notificationModal.setDescription(task.getDescription());
            notificationModal.setDueDate(task.getDueDate());
            notificationModal.setPriority(task.getPriority());
            notificationModal.setCreatedBy(task.getCreatedBy());
            notificationModal.setAssignedTo(task.getAssignedTo());
            notificationModal.setProject(task.getProject());
            notificationModal.setCategory(task.getCategory());
            notificationModal.setStatus(task.getStatus());

            kafkaProducer.sendMessage(notificationModal);

        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, User user) {
        Task existingTask = taskRepository.findById(id).get();
        if (existingTask.getCreatedBy() != user) {
            throw new IllegalStateException("Task deletion requires task owner's authority");
        }

        TaskNotificationModal notificationModal = new TaskNotificationModal();
        notificationModal.setNotificationMessage("task has been deleted");
        notificationModal.setName(existingTask.getName());
        notificationModal.setDescription(existingTask.getDescription());
        notificationModal.setDueDate(existingTask.getDueDate());
        notificationModal.setPriority(existingTask.getPriority());
        notificationModal.setCreatedBy(existingTask.getCreatedBy());
        notificationModal.setAssignedTo(existingTask.getAssignedTo());
        notificationModal.setProject(existingTask.getProject());
        notificationModal.setCategory(existingTask.getCategory());
        notificationModal.setStatus(existingTask.getStatus());

        kafkaProducer.sendMessage(notificationModal);

        taskRepository.delete(existingTask);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).get();
    }

    public List<Comment> getComments(Long id) {
        return commentRepository.findByTaskId(id);
    }
}
