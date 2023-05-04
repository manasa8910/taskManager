package com.taskManager.kafka;

import com.taskManager.modal.TaskNotificationModal;
import org.springframework.kafka.annotation.KafkaListener;

public class JsonKafkaConsumer {
    @KafkaListener(topics = "task_notification", groupId = "my-group")  //subscribe to the topic
    public void consume(TaskNotificationModal notificationModal) { //spring kafka provided JSonDeserializer will convert User Json object to notificationModal java object
    }
}
