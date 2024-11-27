package ru.sidorov.nexigntaskservice.service.abstracts;

import ru.sidorov.nexigntaskservice.models.dto.task.TaskRequest;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskResponse;

public interface TaskService {

    /**
     * Method for registration task in system
     *
     * @param taskRequest {@link TaskRequest}
     */
    void registerTask(TaskRequest taskRequest);

    /**
     * Get a task by id
     *
     * @param taskId {@link Integer}
     * @return {@link TaskResponse}
     */
    TaskResponse getTask(Integer taskId);
}
