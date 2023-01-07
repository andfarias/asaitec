package com.andersonfariasdev.asaitec.service;

import com.andersonfariasdev.asaitec.entity.Fruit;
import com.andersonfariasdev.asaitec.entity.Order;
import com.andersonfariasdev.asaitec.entity.OrderFruit;
import com.andersonfariasdev.asaitec.repository.FruitRepository;
import com.andersonfariasdev.asaitec.repository.OrderFruitRepository;
import com.andersonfariasdev.asaitec.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class OrderService {
    private boolean system = true;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FruitRepository fruitRepository;

    @Autowired
    private OrderFruitRepository orderFruitRepository;

    public void init(Scanner scanner) {
        while (system) {
            System.out.println("What position action do you what to do?");
            System.out.println("0 - Exit");
            System.out.println("1 - Create Order");
            System.out.println("2 - Show orders");

            int action = scanner.nextInt();
            switch (action) {
                case 1 -> createOrder(scanner);
                case 2 -> view();
                default -> system = false;
            }
        }
    }

    private void createOrder(Scanner scanner) {
        Order order = new Order();

        List<OrderFruit> fruits = addFruitToOrder(scanner, order);
        order.setFruits(fruits);

        orderRepository.save(order);
        orderFruitRepository.saveAll(fruits);
        System.out.println("Successful order");

        System.out.println("Successfully saved product!\n");
    }

    private List<OrderFruit> addFruitToOrder(Scanner scanner, Order order) {
        boolean addProducts = true;
        List<OrderFruit> orderFruits = new ArrayList<>();

        while (addProducts) {
            System.out.println("Write id fruit to add to the order (To exit type 0)");
            int fruitId = scanner.nextInt();

            if (fruitId != 0) {
                Optional<Fruit> fruit = fruitRepository.findById(fruitId);

                System.out.println(fruit.get().getName() + " added!");

                System.out.println("Write quantity of " + fruit.get().getName());
                int quantity = scanner.nextInt();

                OrderFruit orderFruit = new OrderFruit(order, fruit.get(), quantity);

                orderFruits.add(orderFruit);
            } else {
                addProducts = false;
            }
        }

        return orderFruits;
    }

    private void view() {
        Iterable<Order> orders = orderRepository.findAll();
        System.out.println("ID, PRODUCT, PRICE");
        orders.forEach(System.out::println);
    }

}
