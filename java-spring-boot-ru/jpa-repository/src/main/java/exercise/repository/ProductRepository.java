package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetweenOrderByPriceAsc(int priceStart, int priceEnd);

    List<Product> findByPriceLessThanEqualOrderByPriceAsc(int max);

    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(int min);
}
