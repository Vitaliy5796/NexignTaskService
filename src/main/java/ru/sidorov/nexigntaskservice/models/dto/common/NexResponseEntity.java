package ru.sidorov.nexigntaskservice.models.dto.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sidorov.nexigntaskservice.models.enums.Result;

public class NexResponseEntity<T> extends ResponseEntity<OperationResult<T>> {

    public NexResponseEntity(HttpStatus httpStatus, Result operationResult, T operationResultObject, String operationInfo) {
        super(new OperationResult<>(operationResult, operationResultObject, operationInfo), httpStatus);
    }
}
