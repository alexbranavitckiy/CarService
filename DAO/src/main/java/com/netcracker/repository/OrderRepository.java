package com.netcracker.repository;


import com.netcracker.order.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Orders, UUID> {
}
