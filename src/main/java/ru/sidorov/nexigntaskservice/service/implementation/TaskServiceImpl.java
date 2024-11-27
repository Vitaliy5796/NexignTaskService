package ru.sidorov.nexigntaskservice.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sidorov.nexigntaskservice.mappers.TaskMapper;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskRequest;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskResponse;
import ru.sidorov.nexigntaskservice.models.entities.Task;
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
    public void registerTask(TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);

        taskRepository.save(task);

        taskWorker.executeTask(task);
    }

    @Override
    public TaskResponse getTask(Integer taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundTaskException(taskId));
        return taskMapper.toTaskResponse(task);
    }
}
