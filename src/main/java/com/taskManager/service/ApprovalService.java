package com.taskManager.service;

import com.taskManager.entity.Task;
import com.taskManager.entity.User;
import com.taskManager.kafka.JsonKafkaProducer;
import com.taskManager.modal.TaskNotificationModal;
import com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JsonKafkaProducer kafkaProducer;

    public void approval(Long id, boolean approve, User user){
        Task task = taskRepository.findById(id).get();
        task.approveTask(user,approve);

        TaskNotificationModal notificationModal = new TaskNotificationModal();
        notificationModal.setNotificationMessage("task approval has been updated");
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

}
