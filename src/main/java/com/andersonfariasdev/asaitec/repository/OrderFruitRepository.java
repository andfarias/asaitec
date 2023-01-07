package com.andersonfariasdev.asaitec.repository;

import com.andersonfariasdev.asaitec.entity.OrderFruit;
import com.andersonfariasdev.asaitec.entity.OrderFruitPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFruitRepository extends JpaRepository<OrderFruit, OrderFruitPK> {
}