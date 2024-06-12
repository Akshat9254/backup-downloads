package com.reddit.handler;

import com.reddit.dto.ApiResponse;
import com.reddit.dto.ErrorDto;
import com.reddit.enums.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class UserServiceExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleSqlIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception) {
        List<ErrorDto> errors = new ArrayList<>();
        log.info(exception.getMessage());
        log.info(exception.getLocalizedMessage());
        log.info(exception.getSQLState());
        return ApiResponse
                .builder()
                .status(ResponseType.FAILURE)
                .build();
    }
}
