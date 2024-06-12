package com.reddit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reddit.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {
    private ResponseType status;
    private List<ErrorDto> errors;
    private T results;
}
