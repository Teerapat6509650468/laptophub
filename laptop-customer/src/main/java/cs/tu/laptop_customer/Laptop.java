package cs.tu.laptop_customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Laptop {

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