package cs.tu.laptop_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/laptops")
@CrossOrigin(origins = "*") // (Optional) Allow cross-origin for easy testing
public class LaptopController {

    private final LaptopService laptopService;
    private boolean laptopsDisplayEnabled = false;

    @Autowired
    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping
    public List<Laptop> getAllLaptops() {
        if (!laptopsDisplayEnabled) {
            return new ArrayList<>();
        }
        return laptopService.getAllLaptops();
    }

    @PostMapping("/enable-display")
    public ResponseEntity<String> enableLaptopDisplay() {
        laptopsDisplayEnabled = true;
        return ResponseEntity.ok("Laptop display enabled");
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserveLaptop(@PathVariable Long id) {
        boolean success = laptopService.reserveLaptop(id);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body("Laptop reserved successfully!.");
            
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Laptop is already reserved or not found.");
        }
    }
}
