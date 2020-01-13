package courseWork.storage;


import org.springframework.data.jpa.repository.JpaRepository;
import courseWork.logic.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}