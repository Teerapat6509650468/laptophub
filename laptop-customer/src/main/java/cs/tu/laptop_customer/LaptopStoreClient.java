package cs.tu.laptop_customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LaptopStoreClient {
    private final RestTemplate restTemplate;
    
    private final String LAPTOP_STORE_BASE_URL = "http://localhost:8080/laptops"; // Replace with the actual base URL

    @Autowired
    public LaptopStoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<?> getAllLaptops() {
        return restTemplate.getForObject(LAPTOP_STORE_BASE_URL, List.class);
    }

    public String reserveLaptop(Long id) {
        String url = LAPTOP_STORE_BASE_URL + "/" + id + "/reserve";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        return response.getBody();
    }
}
