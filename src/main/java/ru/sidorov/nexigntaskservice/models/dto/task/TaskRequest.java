package ru.sidorov.nexigntaskservice.models.dto.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@ApiModel(description = "Задача для запроса")
public class TaskRequest {

    @ApiModelProperty(notes = "Имя задачи")
    @NotBlank
    private String name;

    @ApiModelProperty(notes = "Идемпотентный ключ задачи")
    @NotBlank
    private String idempotencyKey;

    @ApiModelProperty(notes = "Время выполнения")
    @Positive
    private Long duration;
}
