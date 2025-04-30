package cs.tu.laptop_customer;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}