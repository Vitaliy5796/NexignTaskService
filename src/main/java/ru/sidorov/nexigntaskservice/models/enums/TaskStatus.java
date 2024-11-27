package ru.sidorov.nexigntaskservice.models.enums;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Статус задачи")
public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    FAILED
}
