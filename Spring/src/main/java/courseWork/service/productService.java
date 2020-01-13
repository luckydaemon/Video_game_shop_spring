package courseWork.service;

import courseWork.logic.Product;
import courseWork.storage.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class productService{

    private ProductRepo productRepository;
    @Autowired
    public productService(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}
