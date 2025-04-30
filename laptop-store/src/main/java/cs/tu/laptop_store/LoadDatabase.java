package cs.tu.laptop_store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner loadData(LaptopRepository laptopRepository) {
        return args -> {
            laptopRepository.save(new Laptop("Dell", "XPS 13", "Intel i7", 16, 512, 1200.00));
            laptopRepository.save(new Laptop("Apple", "MacBook Air M2", "Apple M2", 16, 256, 1100.00));
            laptopRepository.save(new Laptop("HP", "Spectre x360", "Intel i7", 16, 512, 1300.00));
            laptopRepository.save(new Laptop("Lenovo", "ThinkPad X1 Carbon", "Intel i7", 16, 1024, 1600.00));
            laptopRepository.save(new Laptop("Asus", "Zenbook 14", "AMD Ryzen 7", 16, 512, 1000.00));
            laptopRepository.save(new Laptop("Acer", "Swift 5", "Intel i5", 8, 512, 900.00));
            laptopRepository.save(new Laptop("Microsoft", "Surface Laptop 5", "Intel i5", 8, 256, 1200.00));
            laptopRepository.save(new Laptop("Razer", "Blade 15", "Intel i7", 32, 1024, 2500.00));
            laptopRepository.save(new Laptop("MSI", "Prestige 14", "Intel i7", 16, 512, 1400.00));
            laptopRepository.save(new Laptop("Gigabyte", "Aero 15", "Intel i7", 16, 1024, 1800.00));
        };
    }
}
