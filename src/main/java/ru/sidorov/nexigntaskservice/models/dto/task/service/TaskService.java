package ru.sidorov.nexigntaskservice.models.dto.task.service;

import org.springframework.kafka.annotation.KafkaListener;

public class TaskService {

    @KafkaListener(topics = "tasks", groupId = "task-service")
    public void consumeTask(String taskMessage) {
        
    }
}
