package cart.repository;

import cart.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    void update(Product product);

    Product findById(long id);

    void deleteById(long id);
}
