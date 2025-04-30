package cs.tu.laptop_customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/laptops")
public class LaptopCustomerController {

    private final LaptopStoreClient laptopStoreClient;

    @Autowired
    public LaptopCustomerController(LaptopStoreClient laptopStoreClient) {
        this.laptopStoreClient = laptopStoreClient;
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllLaptops() {
        List<?> laptops = laptopStoreClient.getAllLaptops();
        return ResponseEntity.ok(laptops);
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserveLaptop(@PathVariable Long id) {
        String response = laptopStoreClient.reserveLaptop(id);
        return ResponseEntity.ok(response);
    }
}
