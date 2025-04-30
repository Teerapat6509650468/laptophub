package cs.tu.laptop_customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer("John Doe", "john.doe@example.com", "555-1234", "123 Elm St"));
            customerRepository.save(new Customer("Jane Smith", "jane.smith@example.com", "555-2345", "456 Oak St"));
            customerRepository.save(new Customer("James Johnson", "james.johnson@example.com", "555-3456", "789 Pine St"));
            customerRepository.save(new Customer("Emily Davis", "emily.davis@example.com", "555-4567", "101 Maple St"));
            customerRepository.save(new Customer("Michael Brown", "michael.brown@example.com", "555-5678", "202 Cedar St"));
            customerRepository.save(new Customer("Sarah Wilson", "sarah.wilson@example.com", "555-6789", "303 Birch St"));
            customerRepository.save(new Customer("David Miller", "david.miller@example.com", "555-7890", "404 Willow St"));
            customerRepository.save(new Customer("Laura Taylor", "laura.taylor@example.com", "555-8901", "505 Redwood St"));
            customerRepository.save(new Customer("William Anderson", "william.anderson@example.com", "555-9012", "606 Pinewood St"));
            customerRepository.save(new Customer("Olivia Moore", "olivia.moore@example.com", "555-0123", "707 Cherry St"));
        };
    }
}