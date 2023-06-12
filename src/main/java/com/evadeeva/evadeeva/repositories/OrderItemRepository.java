package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.OrderItem;
import com.evadeeva.evadeeva.models.Orders;
import com.evadeeva.evadeeva.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findBySizeAndAndOrders(Size size, Orders orders);
}
