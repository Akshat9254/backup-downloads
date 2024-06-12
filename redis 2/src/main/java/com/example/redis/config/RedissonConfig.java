package com.example.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RGeo;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        return Redisson.create();
    }

    @Bean
    public RBloomFilter<String> userBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("bloom-demo");
        bloomFilter.tryInit(1000, 0.05);
        return bloomFilter;
    }

    @Bean
    public RGeo<String> userGeo(RedissonClient redissonClient) {
        return redissonClient().getGeo("geo-demo");
    }
}
