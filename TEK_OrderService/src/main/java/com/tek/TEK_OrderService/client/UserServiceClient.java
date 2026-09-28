package com.tek.TEK_OrderService.client;

import com.tek.TEK_OrderService.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client used by Order Service to call User Service over HTTP.
 * The "name" attribute must match the value of spring.application.name
 * configured in TEK-UserService's application.yaml (TEK-UserService),
 * since that is the service-id it registers with Eureka. It must also
 * be a legal hostname (letters, digits, hyphens only — no underscores),
 * or Spring Cloud OpenFeign refuses to start with
 * "Service id not legal hostname". Spring Cloud LoadBalancer resolves
 * this name to an actual host:port at call time, so no hardcoded URL
 * is needed even though the two services use separate databases.
 */
@FeignClient(name = "TEK-UserService")
public interface UserServiceClient {

    @GetMapping("/api/v1/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
