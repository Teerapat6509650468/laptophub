package cs.tu.laptop_store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/customers")
public class LaptopStoreController {

    private final LaptopCustomerClient laptopCustomerClient;

    @Autowired
    public LaptopStoreController(LaptopCustomerClient laptopCustomerClient) {
        this.laptopCustomerClient = laptopCustomerClient;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllCustomers() {
        List<?> customers = laptopCustomerClient.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCustomer(@RequestBody Object customer) {
        Object savedCustomer = laptopCustomerClient.addCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }
}