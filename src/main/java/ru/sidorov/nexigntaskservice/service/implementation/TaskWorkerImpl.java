package ru.sidorov.nexigntaskservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.sidorov.nexigntaskservice.models.entities.Task;
import ru.sidorov.nexigntaskservice.models.enums.TaskStatus;
import ru.sidorov.nexigntaskservice.repositories.TaskRepository;
import ru.sidorov.nexigntaskservice.service.abstracts.TaskWorker;

@Service
@RequiredArgsConstructor
public class TaskWorkerImpl implements TaskWorker {

    private final TaskRepository taskRepository;

    @Override
    @Async
    public void executeTask(Task task) {
        try {
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskRepository.save(task);

            Thread.sleep(task.getDuration());

            task.setStatus(TaskStatus.COMPLETED);
            task.setResult("Task completed successfully");
        } catch (InterruptedException e) {
            task.setStatus(TaskStatus.FAILED);
            task.setResult("Task execution interrupted");
        } finally {
            taskRepository.save(task);
        }
    }
}
