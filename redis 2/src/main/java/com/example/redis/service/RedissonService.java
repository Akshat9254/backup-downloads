package com.example.redis.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.GeoEntry;
import org.redisson.api.GeoOrder;
import org.redisson.api.GeoPosition;
import org.redisson.api.GeoUnit;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RGeo;
import org.redisson.api.geo.GeoSearchArgs;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedissonService {
    private final RBloomFilter<String> bloomFilter;
    private final RGeo<String> geo;

    public void addToBloomFilter(String value) {
        bloomFilter.add(value);
    }

    public boolean mightContain(String value) {
        return bloomFilter.contains(value);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addToGeoSpatial() {
        geo.clear();
        geo.add(
                new GeoEntry(77.594566, 12.971599 , "Bangalore"),
                new GeoEntry(78.46004486083984,17.41339874267578 , "Hyderabad"),
                new GeoEntry(-0.1440551, 51.4893335, "London"),
                new GeoEntry(72.878176, 19.0785451, "Mumbai"),
                new GeoEntry(77.2090057, 28.6138954, "New Delhi"),
                new GeoEntry(-74.0060152, 40.7127281, "New York"),
                new GeoEntry(-118.755997, 36.7014631, "California"),
                new GeoEntry(2.320041, 48.8588897, "Paris"),
                new GeoEntry(55.2924914, 25.2653471, "Dubai")
        );
    }

    public Map<String, GeoPosition> getNearbyCitiesWithPositions(
            double longitude, double latitude, int distance) {
        return geo.searchWithPosition(
                GeoSearchArgs
                        .from(longitude, latitude)
                        .radius(distance, GeoUnit.KILOMETERS)
                        .order(GeoOrder.ASC)
        );
    }

    public Map<String, Double> getNearbyCitiesWithDistance(
            double longitude, double latitude, int distance) {
        return geo.searchWithDistance(
                GeoSearchArgs
                        .from(longitude, latitude)
                        .radius(distance, GeoUnit.KILOMETERS)
                        .order(GeoOrder.ASC)
        );
    }

    public Double getDistanceBetween(String source, String destination) {
        return geo.dist(source, destination, GeoUnit.KILOMETERS);
    }
}