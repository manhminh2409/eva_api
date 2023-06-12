package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Orders;
import com.evadeeva.evadeeva.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByUserAndType(long userId, int type);
    Orders findByUserAndType(User user, int type);
}
