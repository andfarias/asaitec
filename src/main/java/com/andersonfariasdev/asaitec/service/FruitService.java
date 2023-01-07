package com.andersonfariasdev.asaitec.service;

import com.andersonfariasdev.asaitec.entity.Fruit;
import com.andersonfariasdev.asaitec.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class FruitService {
    private boolean system = true;

    @Autowired
    private FruitRepository fruitRepository;

    public void init(Scanner scanner) {
        while (system) {
            System.out.println("What postion action do you what to do?");
            System.out.println("0 - Exit");
            System.out.println("1 - Save");
            System.out.println("2 - Update");
            System.out.println("3 - Show fruits");
            System.out.println("4 - Delete");

            int action = scanner.nextInt();
            switch (action) {
                case 1 -> save(scanner);
                case 3 -> view();
                case 4 -> delete(scanner);
                default -> system = false;
            }
        }
    }

    private void save(Scanner scanner) {
        System.out.println("Write fruit name:");
        String fruitName = scanner.next();


        System.out.println("Write the price:");
        Double price = scanner.nextDouble();

        Fruit fruit = new Fruit();
        fruit.setName(fruitName);
        fruit.setPrice(price);
        fruitRepository.save(fruit);

        System.out.println("Successfully saved product!\n");
    }

    private void view() {
        Iterable<Fruit> fruits = fruitRepository.findAll();
        System.out.println("ID, PRODUCT, PRICE");
        fruits.forEach(System.out::println);
    }

    private void delete(Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        fruitRepository.deleteById(id);
        System.out.println("Deleted");
    }
}
