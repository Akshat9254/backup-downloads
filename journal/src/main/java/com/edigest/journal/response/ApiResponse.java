package com.edigest.journal.response;

public record ApiResponse<T>(
        Boolean success,
        Integer code,
        String message,
        T data
) {
}
