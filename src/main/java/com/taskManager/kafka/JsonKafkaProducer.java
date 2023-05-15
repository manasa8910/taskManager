package com.taskManager.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendMessage(TaskNotificationModal notificationModal) {

        Message<String> message = null;
        try {
            message = MessageBuilder
                    .withPayload(objectMapper.writeValueAsString(notificationModal))   //payload is same as modal...simple pojo
                    .setHeader(KafkaHeaders.TOPIC, "task")
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(message);
    }
}
