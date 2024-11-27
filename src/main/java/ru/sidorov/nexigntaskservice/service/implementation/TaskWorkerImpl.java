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

            Task saveTaskInProgress = taskRepository.save(getTask);
            log.info("Task with idempotency key {} IN_PROGRESS", idempotencyKey);

            Thread.sleep(getTask.getDuration()); // Имитация выполнения

            saveTaskInProgress.setStatus(TaskStatus.COMPLETED);
            saveTaskInProgress.setResult("Task completed successfully");

            taskRepository.save(saveTaskInProgress);
            log.info("Task with idempotency key {} COMPLETED", idempotencyKey);
        } catch (InterruptedException e) {
            getTask.setStatus(TaskStatus.FAILED);
            getTask.setResult("Task execution interrupted");
            taskRepository.save(getTask);
            log.info("Task with idempotency key {} FAILED", idempotencyKey);
        }
    }
}
