package com.tek.TEK_OrderService.service;

import com.tek.TEK_OrderService.Resilience.UserServiceResilience;
import com.tek.TEK_OrderService.dto.OrderRequestDTO;
import com.tek.TEK_OrderService.dto.OrderResponseDTO;
import com.tek.TEK_OrderService.dto.UserDTO;
import com.tek.TEK_OrderService.entity.Order;
import com.tek.TEK_OrderService.repository.OrderRepository;
import io.github.resilience4j.ratelimiter.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserServiceResilience userServiceResilience;

    @Autowired
    RateLimiter orderRateLimiter;

    @Override
    public OrderResponseDTO save(OrderRequestDTO orderRequestDTO) {

        Supplier<OrderResponseDTO> orderSupplier = () -> {

            UserDTO user =
                    userServiceResilience.getUser(
                            orderRequestDTO.getUserId()
                    );

            Order order = new Order();

            order.setUserId(orderRequestDTO.getUserId());
            order.setProduct(orderRequestDTO.getProduct());
            order.setAmount(orderRequestDTO.getAmount());

            Order savedOrder = orderRepository.save(order);

            return toResponseDTO(savedOrder, user);
        };

        Supplier<OrderResponseDTO> rateLimitedSupplier =
                RateLimiter.decorateSupplier(
                        orderRateLimiter,
                        orderSupplier
                );

        return rateLimitedSupplier.get();
    }

    @Override
    public Optional<OrderResponseDTO> getOrderDetails(Long id) {

        return orderRepository.findById(id)
                .map(order ->
                        toResponseDTO(
                                order,
                                userServiceResilience.getUser(
                                        order.getUserId()
                                )
                        )
                );
    }

    private OrderResponseDTO toResponseDTO(
            Order order,
            UserDTO user) {

        return new OrderResponseDTO(
                order.getId(),
                order.getProduct(),
                order.getAmount(),
                user
        );
    }
}