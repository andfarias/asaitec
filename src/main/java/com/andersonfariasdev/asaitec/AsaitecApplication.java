package com.andersonfariasdev.asaitec;

import com.andersonfariasdev.asaitec.service.FruitService;
import com.andersonfariasdev.asaitec.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Scanner;

@SpringBootApplication
@EnableSpringDataWebSupport
public class AsaitecApplication implements CommandLineRunner {

	private boolean system = true;

	@Autowired
	private FruitService fruitService;

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(AsaitecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("What information do you whant to execute?");
			System.out.println("0 - Exit");
			System.out.println("1 - Fruits");
			System.out.println("2 - Orders");

			final int function = scanner.nextInt();
			switch (function) {
				case 1 -> fruitService.init(scanner);
				case 2 -> orderService.init(scanner);

//				case 3:
//					unidadeTrabalhoService.inicial(scanner);
//					break;
//				case 4:
//					relatoriosService.inicial(scanner);
//					break;
//				case 5:
//					relatorioFuncionarioDinamico.inicial(scanner);
//					break;
				default -> {
					System.out.println("End");
					system = false;
				}
			}
		}
	}
}
