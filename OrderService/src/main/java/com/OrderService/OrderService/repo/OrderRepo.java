package com.OrderService.OrderService.repo;

import com.OrderService.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrderRepo extends JpaRepository<Order, Long>
{

}