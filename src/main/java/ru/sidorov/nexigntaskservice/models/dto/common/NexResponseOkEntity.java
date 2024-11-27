package ru.sidorov.nexigntaskservice.models.dto.common;

import org.springframework.http.HttpStatus;
import ru.sidorov.nexigntaskservice.models.enums.Result;

public class NexResponseOkEntity<T> extends NexResponseEntity<T> {

    public NexResponseOkEntity(HttpStatus httpStatus, T operationResultObject)
    {
        super(httpStatus, Result.OK, operationResultObject, null);
    }

    public NexResponseOkEntity(T operationResultObject)
    {
        this(HttpStatus.OK, operationResultObject);
    }

    public NexResponseOkEntity()
    {
        this(HttpStatus.OK, null);
    }
}
