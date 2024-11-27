package ru.sidorov.nexigntaskservice.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.sidorov.nexigntaskservice.models.entities.Task;
import ru.sidorov.nexigntaskservice.models.enums.TaskStatus;
import ru.sidorov.nexigntaskservice.models.exception.NotFoundTaskException;
import ru.sidorov.nexigntaskservice.repositories.TaskRepository;
import ru.sidorov.nexigntaskservice.service.abstracts.TaskWorker;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskWorkerImpl implements TaskWorker {

    private final TaskRepository taskRepository;

    @Override
    @Async
    @Synchronized
    public void executeTask(String idempotencyKey) {
        Task getTask = new Task();
        try {
            getTask = taskRepository.findByIdempotencyKey(idempotencyKey).orElseThrow(() -> new NotFoundTaskException(idempotencyKey));
            getTask.setStatus(TaskStatus.IN_PROGRESS);
            getTask.setResult("Task IN_PROGRESS");

            log.info("[IN_PROGRESS] Attempting to save task with idempotency key: {}", idempotencyKey);
            Task saveTaskInProgress = taskRepository.save(getTask);
            log.info("[IN_PROGRESS] Task with idempotency key {} saved successfully", idempotencyKey);

            Thread.sleep(getTask.getDuration()); // Имитация выполнения

            saveTaskInProgress.setStatus(TaskStatus.COMPLETED);
            saveTaskInProgress.setResult("Task completed successfully");

            log.info("[finally] Attempting to save task with idempotency key: {}", idempotencyKey);
            taskRepository.save(saveTaskInProgress);
            log.info("[finally] Task with idempotency key {} saved successfully", idempotencyKey);
        } catch (InterruptedException e) {
            getTask.setStatus(TaskStatus.FAILED);
            getTask.setResult("Task execution interrupted");
        }
    }
}
