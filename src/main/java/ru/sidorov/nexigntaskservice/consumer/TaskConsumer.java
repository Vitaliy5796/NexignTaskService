package ru.sidorov.nexigntaskservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskRequest;
import ru.sidorov.nexigntaskservice.models.exception.DuplicateTaskException;
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
        } catch (DuplicateTaskException e) {
            log.error("JsonProcessingException: {}", e.getMessage());
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("tasks_idempotency_key_key")) {
                log.warn("Duplicate key detected while consuming Kafka message: {}", taskMessage);
            } else {
                throw e;
            }
        } catch (Exception e) {
            log.error("Error processing task message", e);
            throw new RuntimeException("Error processing task message", e);
        }
    }
}
