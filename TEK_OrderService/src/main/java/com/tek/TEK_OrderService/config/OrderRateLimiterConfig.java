package com.tek.TEK_OrderService.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OrderRateLimiterConfig {

    @Bean
    public RateLimiter orderRateLimiter() {

        RateLimiterConfig config =
                RateLimiterConfig.custom()
                        .limitForPeriod(5)
                        .limitRefreshPeriod(Duration.ofSeconds(10))
                        .timeoutDuration(Duration.ZERO)
                        .build();

        return RateLimiter.of(
                "orderRateLimiter",
                config
        );
    }
}