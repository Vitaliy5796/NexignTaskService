package ru.sidorov.nexigntaskservice.models.dto.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Задача для ответа")
public class TaskResponse {
    @ApiModelProperty(notes = "Уникальный идентификатор задачи")
    private Long id;
    @ApiModelProperty(notes = "Название задачи")
    private String name;
    @ApiModelProperty(notes = "Статус задачи")
    private String status;
    @ApiModelProperty(notes = "Результат задачи")
    private String result;
}
