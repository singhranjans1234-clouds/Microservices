package com.tek.TEK_OrderService.service;

import com.tek.TEK_OrderService.dto.OrderRequestDTO;
import com.tek.TEK_OrderService.dto.OrderResponseDTO;

import java.util.Optional;

public interface OrderService {

    OrderResponseDTO save(OrderRequestDTO orderRequestDTO);

    Optional<OrderResponseDTO> getOrderDetails(Long id);
}