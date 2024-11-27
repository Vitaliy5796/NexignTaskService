package ru.sidorov.nexigntaskservice.models.dto.common;

import org.springframework.http.HttpStatus;
import ru.sidorov.nexigntaskservice.models.enums.Result;
import ru.sidorov.nexigntaskservice.models.exception.TaskRestException;

public class NexResponseErrorEntity<T> extends NexResponseEntity<T> {

    public NexResponseErrorEntity(HttpStatus httpStatus, String operationInfo) {
        super(httpStatus, Result.ERROR, null, operationInfo);
    }

    public NexResponseErrorEntity(String operationInfo) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, operationInfo);
    }

    public NexResponseErrorEntity(TaskRestException e) {
        this(e.getHttpStatus(), e.getMessage());
    }

    public NexResponseErrorEntity(Exception e) {
        this(e.getMessage());
    }
}
