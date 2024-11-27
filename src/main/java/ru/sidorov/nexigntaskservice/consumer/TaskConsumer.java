package ru.sidorov.nexigntaskservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskRequest;
import ru.sidorov.nexigntaskservice.service.abstracts.TaskService;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskConsumer {

    private final ObjectMapper objectMapper;
    private final TaskService taskService;

    @KafkaListener(topics = "tasks", groupId = "task-service")
    public void consumeTask(String taskMessage) {
        try {
            TaskRequest taskRequest = objectMapper.readValue(taskMessage, TaskRequest.class);
            taskService.registerTask(taskRequest);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException: {}", e.getMessage());
        }
    }
}
