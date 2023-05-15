package com.taskManager.service;

import com.taskManager.entity.Comment;
import com.taskManager.entity.Status;
import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import com.taskManager.kafka.JsonKafkaProducer;
import com.taskManager.modal.TaskNotificationModal;
import com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyTasksService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JsonKafkaProducer kafkaProducer;

    public List<Task> findByCompleted(boolean completed, User user) {
        return taskRepository.findByCompletedAndAssignedTo(completed, user);
    }

    public void setStatus(Status status, User user, Long id) {
        Task task = taskRepository.findById(id).get();
        task.setStatus(status,user);

        TaskNotificationModal notificationModal = new TaskNotificationModal();
        notificationModal.setNotificationMessage("Status has been updated");
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

        taskRepository.save(task);

    }

    public  void setComment(Comment comment,Long id){
        Task task = taskRepository.findById(id).get();
        task.addComment(comment);

        TaskNotificationModal notificationModal = new TaskNotificationModal();
        notificationModal.setNotificationMessage("Received a new comment on the task");
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

        taskRepository.save(task);
    }

}
