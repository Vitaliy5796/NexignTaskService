package ru.sidorov.nexigntaskservice.models.dto.task;

import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank
    private String name;

    @Positive
    private Integer duration;
}
