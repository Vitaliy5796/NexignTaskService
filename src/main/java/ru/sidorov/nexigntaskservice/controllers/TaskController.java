package ru.sidorov.nexigntaskservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.nexigntaskservice.models.dto.common.NexResponseEntity;
import ru.sidorov.nexigntaskservice.models.dto.common.NexResponseErrorEntity;
import ru.sidorov.nexigntaskservice.models.dto.common.NexResponseOkEntity;
import ru.sidorov.nexigntaskservice.models.dto.task.TaskResponse;
import ru.sidorov.nexigntaskservice.service.abstracts.TaskService;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@Api("rest-api")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @ApiOperation(value = "Получение информации о задачи")
    @RequestMapping(value = "/{taskId}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public NexResponseEntity<TaskResponse> getTaskById(@PathVariable("taskId") Integer taskId) {
        log.info("[getTaskById] Starting");
        NexResponseEntity<TaskResponse> responseEntity;

        try {
            responseEntity = new NexResponseOkEntity<>(taskService.getTask(taskId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new NexResponseErrorEntity<>(e);
        }

        log.info("[getTaskById] Done");
        return responseEntity;
    }
}
