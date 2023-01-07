package com.andersonfariasdev.asaitec.repository;

import com.andersonfariasdev.asaitec.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}