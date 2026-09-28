package com.tek.TEK_OrderService.controller;

import com.tek.TEK_OrderService.dto.OrderRequestDTO;
import com.tek.TEK_OrderService.dto.OrderResponseDTO;
import com.tek.TEK_OrderService.service.OrderService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {

        return new ResponseEntity<>(
                orderService.save(orderRequestDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(
            @PathVariable Long id) {

        return orderService.getOrderDetails(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleUserNotFound(
            IllegalArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String> handleRateLimit(
            RequestNotPermitted ex) {

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Too many requests. Please try again later.");
    }
}