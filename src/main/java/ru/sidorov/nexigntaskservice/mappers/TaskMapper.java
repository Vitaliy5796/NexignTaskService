package ru.sidorov.nexigntaskservice.mappers;

import org.mapstruct.Mapper;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskRequest;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskResponse;
import ru.sidorov.nexigntaskservice.models.entities.Task;
import ru.sidorov.nexigntaskservice.models.enums.TaskStatus;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    /**
     * map {@link Task} to {@link TaskResponse}
     *
     * @param task {@link Task}
     * @return {@link TaskResponse}
     */
    TaskResponse toTaskResponse(Task task);

    /**
     * map {@link TaskRequest} to {@link Task}
     *
     * @param taskRequest {@link TaskRequest}
     * @return {@link Task}
     */
    default Task toTask(TaskRequest taskRequest) {
        return Task.builder()
                .name(taskRequest.getName())
                .idempotencyKey(taskRequest.getIdempotencyKey())
                .duration(taskRequest.getDuration())
                .result("Task  PENDING")
                .status(TaskStatus.PENDING)
                .build();
    }
}
