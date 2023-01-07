package com.andersonfariasdev.asaitec.repository;

import com.andersonfariasdev.asaitec.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer> {

    List<Fruit> findByName(String name);
}