package com.tek.TEK_OrderService.repository;

import com.tek.TEK_OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
