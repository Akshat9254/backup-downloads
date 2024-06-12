package com.example.redis.dto;

public record AddToBloomFilterRequest(
        String key,
        String value
) {
}
