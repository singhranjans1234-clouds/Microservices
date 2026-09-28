package com.tek.TEK_OrderService.Resilience;

import com.tek.TEK_OrderService.client.UserServiceClient;
import com.tek.TEK_OrderService.dto.UserDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceResilience {

    @Autowired
    private UserServiceClient userServiceClient;

    @Retry(
            name = "userServiceRetry",
            fallbackMethod = "userServiceFallback"
    )
    @CircuitBreaker(
            name = "userServiceCircuitBreaker",
            fallbackMethod = "userServiceFallback"
    )
    public UserDTO getUser(Long userId) {

        return userServiceClient.getUserById(userId);
    }

    public UserDTO userServiceFallback(
            Long userId,
            Throwable throwable) {

        return new UserDTO(
                userId,
                "User Service Unavailable",
                "unavailable"
        );
    }
}