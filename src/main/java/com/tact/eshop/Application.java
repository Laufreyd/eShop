package com.tact.eshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tact.eshop.entity.Customer;
import com.tact.eshop.entity.Order;
import com.tact.eshop.entity.Product;
import com.tact.eshop.repository.CustomerRepository;
import com.tact.eshop.repository.OrderRepository;
import com.tact.eshop.repository.ProductRepository;

@SpringBootApplication
public class Application {

    private static final Logger log =
            LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner addFixtures(
            CustomerRepository repoCutomer,
            ProductRepository repoProduct,
            OrderRepository repoOrder
            ) {
        return (args) -> {
            repoCutomer.deleteAll();
            repoProduct.deleteAll();

            Customer customer = new Customer("Mickael", "Gaillard");
            Order order = new Order();
            customer.addOrder(order);

            // Add customers.
            log.info("Add Customers fixtures...");
            repoCutomer.save(customer);
            repoCutomer.save(new Customer("Jack", "Bauer"));
            repoCutomer.save(new Customer("Chloe", "O'Brian"));
            repoCutomer.save(new Customer("Kim", "Bauer"));
            repoCutomer.save(new Customer("David", "Palmer"));
            repoCutomer.save(new Customer("Michelle", "Dessler"));

            Product product = new Product("Bmx");
            product.setQuantity(5);

            // Add products.
            log.info("Add Products fixtures...");
            repoProduct.save(product);
            repoProduct.save(new Product("T20"));
            repoProduct.save(new Product("Voltic"));
            repoProduct.save(new Product("Rhino"));
            repoProduct.save(new Product("Mule"));

            order.addProduct(product, 1);
            repoOrder.save(order);

            int i = 0;
            i++;
        };
    }
}
