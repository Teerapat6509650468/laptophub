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
    private boolean reservationEnabled = false;

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

    @PostMapping("/enable-reservation")
    public ResponseEntity<String> enableReservation() {
        reservationEnabled = true;
        return ResponseEntity.ok("Laptop reservation enabled");
    }

    @GetMapping("/reservation-status")
    public ResponseEntity<Boolean> getReservationStatus() {
        return ResponseEntity.ok(reservationEnabled);
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserveLaptop(@PathVariable Long id) {
        if (!reservationEnabled) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Reservations are not enabled yet");
        }
        boolean success = laptopService.reserveLaptop(id);
        if (success) {
            return ResponseEntity.ok("Laptop reserved successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Laptop is already reserved or not found.");
        }
    }
}
