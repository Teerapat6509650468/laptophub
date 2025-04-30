package cs.tu.laptop_store;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LaptopCustomerClient {

    private final RestTemplate restTemplate;

    private static final String LAPTOP_CUSTOMER_BASE_URL = "http://localhost:8081/customers";

    @Autowired
    public LaptopCustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<?> getAllCustomers() {
        return Arrays.asList(restTemplate.getForObject(LAPTOP_CUSTOMER_BASE_URL, Object[].class));
    }

    public Object addCustomer(Object customer) {
        return restTemplate.postForObject(LAPTOP_CUSTOMER_BASE_URL + "/addCustomer", customer, Object.class);
    }
}