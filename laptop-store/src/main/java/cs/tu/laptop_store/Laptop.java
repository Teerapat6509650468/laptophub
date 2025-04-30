package cs.tu.laptop_store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String processor;
    private int ram;
    private int storage;
    private double price;

    // Reserved status for the laptop
    private boolean reserved = false;

	// Custom constructor without id
	public Laptop(String brand, String model, String processor, int ram, int storage, double price) {
		this.brand = brand;
		this.model = model;
		this.processor = processor;
		this.ram = ram;
		this.storage = storage;
		this.price = price;
	}
}	