package ru.sidorov.nexigntaskservice.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.nexigntaskservice.mappers.TaskMapper;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskRequest;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskResponse;
import ru.sidorov.nexigntaskservice.models.entities.Task;
import ru.sidorov.nexigntaskservice.models.exception.DuplicateTaskException;
import ru.sidorov.nexigntaskservice.models.exception.NotFoundTaskException;
import ru.sidorov.nexigntaskservice.repositories.TaskRepository;
import ru.sidorov.nexigntaskservice.service.abstracts.TaskService;
import ru.sidorov.nexigntaskservice.service.abstracts.TaskWorker;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskWorker taskWorker;

    @Override
    @Transactional
    public void registerTask(TaskRequest taskRequest) {
        String idempotencyKey = taskRequest.getIdempotencyKey();

        if (taskRepository.existsByIdempotencyKey(idempotencyKey)) {
            log.info("Task with idempotency key {} already exists", idempotencyKey);
            throw new DuplicateTaskException();
        }

        if (StringUtils.isBlank(idempotencyKey)) {
            throw new IllegalArgumentException("Idempotency key is null or empty");
        }

        Task task = taskMapper.toTask(taskRequest);

        try {
            taskRepository.save(task);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("tasks_idempotency_key_key")) {
                log.warn("Duplicate task with idempotency key found, skipping processing");
                return;
            }
            throw e;
        }

        taskWorker.executeTask(task.getIdempotencyKey());
    }

    @Override
    public TaskResponse getTask(Integer taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundTaskException(taskId));
        return taskMapper.toTaskResponse(task);
    }
}
