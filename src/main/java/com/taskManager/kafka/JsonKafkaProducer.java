package com.taskManager.kafka;

import com.taskManager.modal.TaskNotificationModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service

public class JsonKafkaProducer {

    @Autowired
    private KafkaTemplate<String, TaskNotificationModal> kafkaTemplate;

    public void sendMessage(TaskNotificationModal notificationModal) {


        Message<TaskNotificationModal> message = MessageBuilder
                .withPayload(notificationModal)   //payload is same as modal...simple pojo
                .setHeader(KafkaHeaders.TOPIC, "task_notification")
                .build();

        kafkaTemplate.send(message);
    }
}
