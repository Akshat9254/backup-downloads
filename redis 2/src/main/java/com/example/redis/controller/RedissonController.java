package com.example.redis.controller;

import com.example.redis.dto.AddToBloomFilterRequest;
import com.example.redis.service.RedissonService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.GeoPosition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {
    private final RedissonService service;

    @PostMapping("/bloom")
    public void addToBloomFilter(@RequestBody AddToBloomFilterRequest request) {
        service.addToBloomFilter(request.value());
    }

    @GetMapping("/bloom/check/{value}")
    public boolean checkBloomFilter(@PathVariable String value) {
        return service.mightContain(value);
    }

    @GetMapping("/geo/position")
    public Map<String, GeoPosition> getNearByPositions(
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "long") double longitude,
            @RequestParam(name = "dist") int distance
    ) {
        return service.getNearbyCitiesWithPositions(longitude, latitude, distance);
    }

    @GetMapping("/geo/distance")
    public Map<String, Double> getNearByDistance(
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "long") double longitude,
            @RequestParam(name = "dist") int distance
    ) {
        return service.getNearbyCitiesWithDistance(longitude, latitude, distance);
    }

    @GetMapping("/geo/distance/between")
    public Double getDistanceBetween(
            @RequestParam(name = "src") String source,
            @RequestParam(name = "dest") String destination
    ) {
        return service.getDistanceBetween(source, destination);
    }
}
