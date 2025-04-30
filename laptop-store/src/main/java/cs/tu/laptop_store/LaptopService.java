package cs.tu.laptop_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public boolean reserveLaptop(Long id) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            if (!laptop.isReserved()) {
                laptop.setReserved(true);
                laptopRepository.save(laptop);
                return true;
            }
        }
        return false;
    }
    
}