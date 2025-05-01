package cs.tu.laptop_customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*") 
public class CustomerController {

    private final CustomerService customerService;
    private static boolean dataRequested = false;
    private static boolean customerAdditionEnabled = false;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        dataRequested = true;
        Iterable<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Boolean> getDataRequestStatus() {
        return ResponseEntity.ok(dataRequested);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        if (!customerAdditionEnabled) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Customer savedCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/enable-addition")
    public ResponseEntity<String> enableCustomerAddition() {
        customerAdditionEnabled = true;
        return ResponseEntity.ok("Customer addition enabled");
    }

    @GetMapping("/addition-status")
    public ResponseEntity<Boolean> getCustomerAdditionStatus() {
        return ResponseEntity.ok(customerAdditionEnabled);
    }
}
