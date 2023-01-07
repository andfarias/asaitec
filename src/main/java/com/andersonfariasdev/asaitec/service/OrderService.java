package com.andersonfariasdev.asaitec.service;

import com.andersonfariasdev.asaitec.entity.Fruit;
import com.andersonfariasdev.asaitec.entity.Order;
import com.andersonfariasdev.asaitec.entity.OrderFruit;
import com.andersonfariasdev.asaitec.entity.OrderFruitPK;
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
            System.out.println("What postion action do you what to do?");
            System.out.println("0 - Exit");
            System.out.println("1 - Create Order");
//            System.out.println("2 - Update");
            System.out.println("2 - Show orders");
//            System.out.println("4 - Delete");

            int action = scanner.nextInt();
            switch (action) {
                case 1 -> createOrder(scanner);

//                case 2:
//                    update(scanner);
//                    break;
                case 2 -> view();
//                case 4 -> delete(scanner);
                default -> system = false;
            }
        }
    }

    private void createOrder(Scanner scanner) {
//        boolean foundFruit = false;
//        boolean addProducts = true;




        Order order = new Order();

        List<OrderFruit> fruits = addFruitToOrder(scanner, order);
        order.setFruits(fruits);

        orderRepository.save(order);
        orderFruitRepository.saveAll(fruits);
        System.out.println("Successful order");

//        while (addProducts) {
//            System.out.println("What postion action do you what to do?");
//            System.out.println("0 - Exit");
//            System.out.println("1 - Add product");
//            System.out.println("2 - Checkout");
//
//            case 1 -> createOrder(scanner);

//        while (!foundFruit) {
//            System.out.println("Write id fruit to add to the order:");
//            int id = scanner.nextInt();
//
//            Optional<Fruit> fruit = fruitRepository.findById(id);
//            if (fruit.isEmpty()) {
//                System.out.println("The fruit ("+id+") don't exists. Please repeat the operation:");
//                foundFruit = true;
//            }
//        }


//        System.out.println("Write the quantity of "+fruit.get()+":");
//        Double price = scanner.nextDouble();
//
//        Fruit fruit = new Fruit();
//        fruit.setName(fruitName);
//        fruit.setPrice(price);
//        fruitRepository.save(fruit);

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
//                    fruits.add(fruit.get());

//                    fruit.get();

                    System.out.println(fruit.get().getName() + " added!");

//                    orderFruit.setId(new OrderFruitPK(order, fruit.get()));


                    System.out.println("Write quantity of " + fruit.get().getName());
                    int quantity = scanner.nextInt();
//                    orderFruit.setQuantity(quantity);

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
