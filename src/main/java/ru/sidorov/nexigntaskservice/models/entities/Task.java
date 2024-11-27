package ru.sidorov.nexigntaskservice.models.entities;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sidorov.nexigntaskservice.models.enums.TaskStatus;

@ApiModel(description = "Задача")
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Уникальный идентификатор задачи")
    private Integer id;

    @Column
    @ApiModelProperty(notes = "Имя задачи")
    private String name;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @Column
    @ApiModelProperty(notes = "Продолжительность выполнения задачи")
    private Long duration;


    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Статус задачи")
    private TaskStatus status;

    @Column
    @ApiModelProperty(notes = "Результат выполнения задачи")
    private String result;
}
