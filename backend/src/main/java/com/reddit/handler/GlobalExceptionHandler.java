package com.reddit.handler;

import com.reddit.dto.ApiResponse;
import com.reddit.dto.ErrorDto;
import com.reddit.enums.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        List<ErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDto errorDTO = new ErrorDto(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        apiResponse.setStatus(ResponseType.FAILURE);
        apiResponse.setErrors(errors);
        return apiResponse;
    }
}
